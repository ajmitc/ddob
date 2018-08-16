package ddob.game.table;

import ddob.game.unit.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class USAttackTable {

    public static AttackResult get( int turn, List<USUnit> attackers, GermanUnit target, boolean navalArillery ) {
        int attackStrength = 0;

        for( USUnit attacker: attackers ) {
            attackStrength += attacker.getState().getStrength();
        }

        int targetStrength = target.getState().getStrength();
        if( target.getDepthMarker() != null )
            targetStrength += target.getDepthMarker().getStrength();

        if( hasRequiredWeapons( attackers, target, navalArillery ) ) {
            if( attackStrength < targetStrength ) {
                if( target.getDepthMarker() == null ) {
                    // German gains Depth
                    return AttackResult.GERMAN_GAIN_DEPTH;
                }
                else if( !target.getDepthMarker().isRevealed() ) {
                    return AttackResult.US_DISRUPTED;
                }
                return AttackResult.NO_EFFECT;
            }
            else if( attackStrength == targetStrength ) {
                if( target.getDepthMarker() == null ) {
                    return AttackResult.GERMAN_DISRUPTED;
                }
                else if( !target.getDepthMarker().isRevealed() ) {
                    return AttackResult.NO_EFFECT;
                }
                return AttackResult.GERMAN_DISRUPTED;
            }
            else if( attackStrength > targetStrength && (attackStrength * 2) < targetStrength ) {
                if( target.getDepthMarker() == null ) {
                    return AttackResult.GERMAN_ELIMINATED;
                }
                else if( !target.getDepthMarker().isRevealed() ) {
                    target.getDepthMarker().setRevealed( true );
                    return get( turn, attackers, target, navalArillery );
                }
                return AttackResult.GERMAN_DEPTH_ELIMINATED_AND_UNIT_DISRUPTED;
            }
            else {
                if( target.getDepthMarker() == null ) {
                    return AttackResult.GERMAN_ELIMINATED;
                }
                else if( !target.getDepthMarker().isRevealed() ) {
                    target.getDepthMarker().setRevealed( true );
                    if( target.getDepthMarker().getEffect() == DepthMarkerEffect.TACTICAL_REINFORCEMENT ) {
                        return AttackResult.GERMAN_ELIMINATED_AND_TACTICAL_REINFORCEMENT;
                    }
                    return get( turn, attackers, target, navalArillery );
                }
                if( turn <= 16 ) {
                    return AttackResult.GERMAN_DEPTH_ELIMINATED_AND_UNIT_DISRUPTED;
                }
                return AttackResult.GERMAN_DEPTH_ELIMINATED_AND_UNIT_ELIMINATED;
            }
        }
        else { // Do not have required weapons
            if( attackStrength <= targetStrength ) {
                if( target.getDepthMarker() == null ) {
                    return AttackResult.GERMAN_GAIN_DEPTH_AND_US_DISRUPTED;
                }
                else if( !target.getDepthMarker().isRevealed() ) {
                    target.setRevealed( false );
                    return AttackResult.US_DISRUPTED;
                }
                return AttackResult.US_DISRUPTED;
            }
            else if( attackStrength > targetStrength && (attackStrength * 2) < targetStrength ) {
                if( target.getDepthMarker() == null ) {
                    return AttackResult.GERMAN_GAIN_DEPTH;
                }
                else if( !target.getDepthMarker().isRevealed() ) {
                    return AttackResult.US_DISRUPTED;
                }
                return AttackResult.NO_EFFECT;
            }
            else {
                if( target.getDepthMarker() != null && target.getDepthMarker().isRevealed() ) {
                    return AttackResult.GERMAN_DISRUPTED_AND_OPTIONAL_ATTRITION;
                }
                return AttackResult.GERMAN_DISRUPTED;
            }
        }
    }

    public static boolean hasRequiredWeapons( List<USUnit> attackers, GermanUnit target, boolean navalArtillery ) {
        Set<Weapon> requiredWeapons = new HashSet<>();
        requiredWeapons.addAll( target.getState().getWeapons() );
        if( target.getDepthMarker() != null ) {
            requiredWeapons.addAll( target.getDepthMarker().getWeapons() );
        }

        for( USUnit attacker: attackers ) {
            for( Weapon weapon : attacker.getState().getWeapons() ) {
                if( requiredWeapons.contains( weapon ) ) {
                    requiredWeapons.remove( weapon );
                }
            }
        }

        if( navalArtillery ) {
            if( requiredWeapons.contains( Weapon.NA ) )
                requiredWeapons.remove( Weapon.NA );
            if( requiredWeapons.contains( Weapon.AR ) )
                requiredWeapons.remove( Weapon.AR );
            if( requiredWeapons.contains( Weapon.DE ) )
                requiredWeapons.remove( Weapon.DE );
        }

        return requiredWeapons.size() == 0;
    }

    private USAttackTable(){}
}