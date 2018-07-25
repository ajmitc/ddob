package ddob.game.unit;

public class GermanUnit extends Unit {
    private boolean _revealed;
    private DepthMarker _depthMarker;

    public GermanUnit( UnitType type, String designation ) {
        super( Allegiance.GERMAN, type, designation );
        _revealed = false;
        _depthMarker = null;
    }

    public boolean isRevealed(){ return _revealed; }

    public void setRevealed( boolean v ){ _revealed = v; }

    public DepthMarker getDepthMarker() {
        return _depthMarker;
    }

    public void setDepthMarker( DepthMarker m ) {
        _depthMarker = m;
    }
}
