package ddob.view;

import ddob.Model;
import ddob.game.unit.GermanUnit;
import ddob.game.unit.Unit;
import ddob.game.unit.UnitType;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class CellUnitsGameView extends GameView {
    private static final int BORDER_SPACING = 5;
    private static final int UNIT_SPACING = 5;
    private static final Color BACKGROUND_COLOR = Color.WHITE;

    private List<Unit> _units;
    private boolean _hide;
    private int _ax, _ay, _w, _h;

    public CellUnitsGameView( Model model, View view, List<Unit> units, int px, int py ) {
        super( model, view );
        _units = units;
        _hide = false;
        _ax = px + _view.getGamePanel().getBoardView().getVpx() - 5;
        _ay = py + _view.getGamePanel().getBoardView().getVpy() - 5;
        _w = 100;
        _h = 100;
    }

    @Override
    public boolean mouseMoved( MouseEvent e ) {
        int x = _ax - _view.getGamePanel().getBoardView().getVpx();
        int y = _ay - _view.getGamePanel().getBoardView().getVpy();
        if( e.getX() < x || e.getX() > x + _w || e.getY() < y || e.getY() > y + _h ) {
            setHide( true );
        }
        return true;
    }

    @Override
    public void redraw( Graphics2D g, Dimension panelSize ) {
        if( _units.size() == 0 ) {
            setHide( true );
            return;
        }
        int unitWidth  = _units.get( 0 ).getState().getImage().getWidth();
        int unitHeight = _units.get( 0 ).getState().getImage().getHeight();
        int x = _ax - _view.getGamePanel().getBoardView().getVpx();
        int y = _ay - _view.getGamePanel().getBoardView().getVpy();
        _w = (BORDER_SPACING * 2) + (unitWidth * _units.size()) + (UNIT_SPACING * (_units.size() - 1));
        _h = (BORDER_SPACING * 2) + unitHeight;

        List<BufferedImage> images = new ArrayList<>();

        for( int i = 0; i < _units.size(); ++i ) {
            Unit unit = _units.get( i );
            BufferedImage image = unit.getState().getImage();
            if( unit instanceof GermanUnit && !((GermanUnit) unit).isRevealed() ) {
                image = _model.getGame().getGermanUnitBack( unit.getType() );
            }
            if( unit instanceof GermanUnit ) {
                images.add( image );
                GermanUnit gunit = (GermanUnit) unit;
                if( gunit.getDepthMarker() != null ) {
                    _w += UNIT_SPACING + unitWidth;
                    if( gunit.getDepthMarker().isRevealed() )
                        images.add( gunit.getDepthMarker().getImage() );
                    else
                        images.add( _model.getGame().getDepthMarkerBack( gunit.getDepthMarker().getType() ) );
                }
            }
            else {
                images.add( image );
            }
        }

        // Make sure the view fits on the screen
        if( x + _w > panelSize.width ) {
            x = panelSize.width - _w;
        }
        if( y + _h > panelSize.height ) {
            y = panelSize.height - _h;
        }

        g.setColor( BACKGROUND_COLOR );
        g.fillRect( x, y, _w, _h );
        int ux = x + BORDER_SPACING;
        int uy = y + BORDER_SPACING;
        for( BufferedImage image: images ) {
            g.drawImage( image, null, ux, uy );
            ux += image.getWidth() + UNIT_SPACING;
        }
    }

    public void setHide( boolean v ) {
        _hide = v;
        if( _hide ) {
            _view.getGamePanel().getBoardView().setCellUnitsDisplayed( false );
        }
    }

    public boolean shouldHide() {
        return _hide;
    }
}
