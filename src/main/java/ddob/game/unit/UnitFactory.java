package ddob.game.unit;

import ddob.util.ImageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnitFactory {

    public static List<USUnit> loadUS1stDivisionUnits() {
        List<USUnit> usUnits = new ArrayList<>();

        USUnit unit = new USUnit( Division.US_1, UnitType.INFANTRY, "A/1/16", 6, "ER" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_A_3" ), 6, 1, UnitSymbol.DIAMOND, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_A_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BZ, Weapon.BG, Weapon.RD } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_A_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BZ } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "B/1/16", 6, "ER" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_B_3" ), 6, 1, UnitSymbol.TRIANGLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_B_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BZ, Weapon.BR, Weapon.DE } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_B_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.DE } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "C/1/16", 6, "ER" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_C_3" ), 6, 1, UnitSymbol.CIRCLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_C_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BG, Weapon.MO, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_C_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BG } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "D/1/16", 7, "ER" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_D_3" ), 7, 2, UnitSymbol.TRIANGLE, USUnit.HEAVY_INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_D_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.DE, Weapon.MG, Weapon.MO } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_D_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.MG } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "E/2/16", 1, "ER6" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_E_3" ), 6, 1, UnitSymbol.TRIANGLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_E_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BZ, Weapon.BG, Weapon.RD } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_E_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BG } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "F/2/16", 1, "ER3" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_F_3" ), 6, 1, UnitSymbol.TRIANGLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_F_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BZ, Weapon.BR, Weapon.DE } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_F_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BR } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "G/2/16", 3, "ER2" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_G_3" ), 6, 1, UnitSymbol.CIRCLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_G_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BG, Weapon.MO, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_G_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.MO } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "H/2/16", 4, "ER4" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_H_3" ), 7, 2, UnitSymbol.DIAMOND, USUnit.HEAVY_INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_H_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.DE, Weapon.MG, Weapon.MO } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_H_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.DE } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "I/3/16", 1, "FG2" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_I_3" ), 6, 1, UnitSymbol.DIAMOND, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_I_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BZ, Weapon.BG, Weapon.RD } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_I_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.RD } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "K/3/16", 3, "FG4" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_K_3" ), 6, 1, UnitSymbol.TRIANGLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_K_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BZ, Weapon.BR, Weapon.DE } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_K_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BZ } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "L/3/16", 1, "FG5" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_L_3" ), 6, 1, UnitSymbol.CIRCLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_L_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BG, Weapon.MO, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_L_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BR } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "M/3/16", 4, "FG4" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_M_3" ), 7, 2, UnitSymbol.CIRCLE, USUnit.HEAVY_INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_M_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.DE, Weapon.MO, Weapon.MG } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_M_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.MO } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.ANTI_TANK, "AT/16", 9, "ER" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_AT_1" ), 2, 5, UnitSymbol.TRIANGLE, USUnit.ANTI_TANK_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.ARTILLERY_DUKW, "Can/16", 8, "ER" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_Can_2" ), 3, -1, UnitSymbol.CIRCLE, USUnit.ARTILLERY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_Can_1" ), 1, -1, UnitSymbol.TRIANGLE, USUnit.ARTILLERY_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.HQ, "16", 7, "FG" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_HQ" ), 0, 1, null, USUnit.HQ_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_HQ_CP" ), 0, 1, null, USUnit.HQ_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.SELF_PROPELLED_ARTILLERY, "7FA", 8, "ER" );
        unit.addState( new UnitState( ImageFactory.get( "1st_7FA_4" ), 9, -1, UnitSymbol.DIAMOND, USUnit.ARTILLERY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_7FA_3" ), 6, -1, UnitSymbol.DIAMOND, USUnit.ARTILLERY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_7FA_2" ), 4, -1, UnitSymbol.DIAMOND, USUnit.ARTILLERY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_7FA_2" ), 2, -1, UnitSymbol.DIAMOND, USUnit.ARTILLERY_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "A/1/18", 20,  "1st" );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_1_A_3" ), 6, 1, UnitSymbol.DIAMOND, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_1_A_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BZ, Weapon.BG, Weapon.RD } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_1_A_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BZ } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "B/1/18", 20,  "1st" );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_1_B_3" ), 6, 1, UnitSymbol.TRIANGLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_1_B_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BZ, Weapon.BR, Weapon.DE } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_1_B_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.DE } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "C/1/18", 20,  "1st" );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_1_C_3" ), 6, 1, UnitSymbol.CIRCLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_1_C_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BG, Weapon.MO, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_1_C_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BG } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "D/1/18", 20,  "1st" );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_1_D_3" ), 7, 2, UnitSymbol.DIAMOND, USUnit.HEAVY_INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_1_D_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.DE, Weapon.MG, Weapon.MO } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_1_D_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.MG } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "E/2/18", 16,  "1st" );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_2_E_3" ), 6, 1, UnitSymbol.CIRCLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_2_E_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BZ, Weapon.BG, Weapon.RD } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_2_E_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BG } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "F/2/18", 16,  "1st" );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_2_F_3" ), 6, 1, UnitSymbol.DIAMOND, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_2_F_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BZ, Weapon.BR, Weapon.DE } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_2_F_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BR } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "G/2/18", 16,  "1st" );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_2_G_3" ), 6, 1, UnitSymbol.TRIANGLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_2_G_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BG, Weapon.MO, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_2_G_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.MO } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "H/2/18", 16,  "1st" );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_2_H_3" ), 7, 2, UnitSymbol.CIRCLE, USUnit.HEAVY_INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_2_H_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.DE, Weapon.MG, Weapon.MO } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_2_H_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.DE } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "I/3/18", 22,  "1st" );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_3_I_3" ), 6, 1, UnitSymbol.TRIANGLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_3_I_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BZ, Weapon.BG, Weapon.RD } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_3_I_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.RD } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "K/3/18", 22,  "1st" );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_3_K_3" ), 6, 1, UnitSymbol.CIRCLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_3_K_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BZ, Weapon.BR, Weapon.DE } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_3_K_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BZ } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "L/3/18", 22,  "1st" );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_3_L_3" ), 6, 1, UnitSymbol.DIAMOND, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_3_L_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BG, Weapon.MO, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_3_L_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BR } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "M/3/18", 22,  "1st" );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_3_M_3" ), 7, 2, UnitSymbol.TRIANGLE, USUnit.HEAVY_INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_3_M_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.DE, Weapon.MO, Weapon.MG } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_3_M_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.MO } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.ANTI_TANK, "AT/18", 23, "1st" );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_AT_1" ), 2, 5, UnitSymbol.CIRCLE, USUnit.ANTI_TANK_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.HQ, "18", 22, "1st" );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_HQ" ), 0, 1, null, USUnit.HQ_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_HQ_CP" ), 0, 1, null, USUnit.HQ_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.SELF_PROPELLED_ARTILLERY, "62AFA", 7, "FG" );
        unit.addState( new UnitState( ImageFactory.get( "1st_62AFA_4" ), 9, -1, UnitSymbol.CIRCLE, USUnit.ARTILLERY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_62AFA_3" ), 6, -1, UnitSymbol.CIRCLE, USUnit.ARTILLERY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_62AFA_2" ), 4, -1, UnitSymbol.CIRCLE, USUnit.ARTILLERY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_62AFA_2" ), 2, -1, UnitSymbol.CIRCLE, USUnit.ARTILLERY_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.ARTILLERY, "A-C/81C", 5, "FG" );
        unit.addState( new UnitState( ImageFactory.get( "1st_81C_A_C_2" ), 4, -1, UnitSymbol.TRIANGLE, USUnit.ARTILLERY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_81C_A_C_1" ), 2, -1, UnitSymbol.TRIANGLE, USUnit.ARTILLERY_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.SELF_PROPELLED_ANTI_AIRCRAFT, "A-B/197", 9, "FG" );
        unit.addState( new UnitState( ImageFactory.get( "1st_197_A_B_2" ), 3, 3, UnitSymbol.CIRCLE, USUnit.ANTI_AIRCRAFT_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_197_A_B_1" ), 1, 3, UnitSymbol.CIRCLE, USUnit.ANTI_AIRCRAFT_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.SELF_PROPELLED_ANTI_AIRCRAFT, "C-D/197", 9, "ER" );
        unit.addState( new UnitState( ImageFactory.get( "1st_197_C_D_2" ), 3, 3, UnitSymbol.DIAMOND, USUnit.ANTI_AIRCRAFT_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_197_C_D_1" ), 1, 3, UnitSymbol.DIAMOND, USUnit.ANTI_AIRCRAFT_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.ANTI_AIRCRAFT, "1-2/397", 3, "FG5" );
        unit.addState( new UnitState( ImageFactory.get( "1st_397_1_2_2" ), 4, 3, UnitSymbol.DIAMOND, USUnit.ARTILLERY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_397_1_2_1" ), 2, 3, UnitSymbol.DIAMOND, USUnit.ARTILLERY_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.TANK, "1/A/741", 1, "FG4" );
        unit.addState( new UnitState( ImageFactory.get( "1st_741_A_1_2" ), 4, 5, UnitSymbol.TRIANGLE, USUnit.TANK_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_741_A_1_1" ), 2, 5, UnitSymbol.TRIANGLE, USUnit.TANK_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.TANK, "2/A/741", 1, "ER2" );
        unit.addState( new UnitState( ImageFactory.get( "1st_741_A_1_2" ), 4, 5, UnitSymbol.CIRCLE, USUnit.TANK_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_741_A_1_1" ), 2, 5, UnitSymbol.CIRCLE, USUnit.TANK_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.TANK, "1/B/741", 0, "FG2" );
        unit.addState( new UnitState( ImageFactory.get( "1st_741_B_1_2" ), 4, 5, UnitSymbol.DIAMOND, USUnit.TANK_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_741_B_1_1" ), 2, 5, UnitSymbol.DIAMOND, USUnit.TANK_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.TANK, "2/B/741", 0, "FG5" );
        unit.addState( new UnitState( ImageFactory.get( "1st_741_B_2_2" ), 4, 5, UnitSymbol.TRIANGLE, USUnit.TANK_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_741_B_2_1" ), 2, 5, UnitSymbol.TRIANGLE, USUnit.TANK_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.TANK, "1/C/741", 0, "ER3" );
        unit.addState( new UnitState( ImageFactory.get( "1st_741_C_1_2" ), 4, 5, UnitSymbol.CIRCLE, USUnit.TANK_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_741_C_1_1" ), 2, 5, UnitSymbol.CIRCLE, USUnit.TANK_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.TANK, "2/C/741", 0, "ER5" );
        unit.addState( new UnitState( ImageFactory.get( "1st_741_C_2_2" ), 4, 5, UnitSymbol.DIAMOND, USUnit.TANK_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_741_C_2_1" ), 2, 5, UnitSymbol.DIAMOND, USUnit.TANK_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.ENGINEER, "1-37", -1, "" );
        unit.addState( new UnitState( ImageFactory.get( "1st_Eng_1_37" ), 0, 0, null, null ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.ENGINEER, "336-348", -1, "" );
        unit.addState( new UnitState( ImageFactory.get( "1st_Eng_336_348" ), 0, 0, null, null ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_1, UnitType.GENERAL, "Gen. Wyman", 10, "1st" );
        unit.addState( new UnitState( ImageFactory.get( "1st_Gen_Wyman" ), 0, 1, null, null ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_Gen_Wyman_r" ), 0, 1, null, null ) );
        usUnits.add( unit );

        return usUnits;
    }


    public static List<USUnit> loadUS29thDivisionUnits() {
        List<USUnit> usUnits = new ArrayList<>();

        USUnit unit = new USUnit( Division.US_29, UnitType.RANGER_INFANTRY, "A-B/2R", 5, "DG" );
        unit.addState( new UnitState( ImageFactory.get( "29th_2R_A_B_2" ), 5, 1, UnitSymbol.DIAMOND, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_2R_A_B_1" ), 3, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BZ, Weapon.BG, Weapon.RD } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.RANGER_INFANTRY, "C/2R", 1, "CH2" );
        unit.addState( new UnitState( ImageFactory.get( "29th_2R_C_2" ), 3, 1, UnitSymbol.TRIANGLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_2R_C_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BR, Weapon.BG } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.RANGER_INFANTRY, "A-B/5R", 6, "DG" );
        unit.addState( new UnitState( ImageFactory.get( "29th_5R_A_B_2" ), 5, 1, UnitSymbol.TRIANGLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_5R_A_B_1" ), 3, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BZ, Weapon.BG, Weapon.RD } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.RANGER_INFANTRY, "C-D/5R", 6, "DG" );
        unit.addState( new UnitState( ImageFactory.get( "29th_5R_C_D_2" ), 5, 1, UnitSymbol.CIRCLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_5R_C_D_1" ), 3, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BZ, Weapon.DE, Weapon.BR } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.RANGER_INFANTRY, "E-F/5R", 6, "DG" );
        unit.addState( new UnitState( ImageFactory.get( "29th_5R_E_F_2" ), 5, 1, UnitSymbol.DIAMOND, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_5R_E_F_1" ), 3, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.MO, Weapon.BG, Weapon.BR } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.SELF_PROPELLED_ARTILLERY, "58AFA", 7, "DW" );
        unit.addState( new UnitState( ImageFactory.get( "29th_58AFA_4" ), 9, -1, UnitSymbol.DIAMOND, USUnit.ARTILLERY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_58AFA_3" ), 6, -1, UnitSymbol.DIAMOND, USUnit.ARTILLERY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_58AFA_2" ), 4, -1, UnitSymbol.DIAMOND, USUnit.ARTILLERY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_58AFA_1" ), 2, -1, UnitSymbol.DIAMOND, USUnit.ARTILLERY_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.ARTILLERY, "B-D/81C", 5, "DW" );
        unit.addState( new UnitState( ImageFactory.get( "29th_81C_B_D_2" ), 4, -1, UnitSymbol.CIRCLE, USUnit.ARTILLERY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_81C_B_D_1" ), 2, -1, UnitSymbol.CIRCLE, USUnit.ARTILLERY_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.ARTILLERY_DUKW, "111FA", 8, "EG" );
        unit.addState( new UnitState( ImageFactory.get( "29th_111FA_4" ), 9, -1, UnitSymbol.TRIANGLE, USUnit.ARTILLERY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_111FA_3" ), 6, -1, UnitSymbol.TRIANGLE, USUnit.ARTILLERY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_111FA_2" ), 4, -1, UnitSymbol.TRIANGLE, USUnit.ARTILLERY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_111FA_1" ), 2, -1, UnitSymbol.TRIANGLE, USUnit.ARTILLERY_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "A/1/115", 16, "29th" );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_1_A_3" ), 6, 1, UnitSymbol.CIRCLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_1_A_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BZ, Weapon.BG, Weapon.RD } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_1_A_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BZ } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "B/1/115", 16, "29th" );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_1_B_3" ), 6, 1, UnitSymbol.DIAMOND, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_1_B_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BZ, Weapon.DE, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_1_B_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.DE } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "C/1/115", 16, "29th" );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_1_C_3" ), 6, 1, UnitSymbol.TRIANGLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_1_C_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.MO, Weapon.BG, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_1_C_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BG } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "D/1/115", 16, "29th" );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_1_D_3" ), 7, 2, UnitSymbol.CIRCLE, USUnit.HEAVY_INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_1_D_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.MO, Weapon.DE, Weapon.MG } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_1_D_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.MG } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "E/2/115", 16, "29th" );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_2_E_3" ), 6, 1, UnitSymbol.DIAMOND, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_2_E_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BZ, Weapon.BG, Weapon.RD } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_2_E_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BG } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "F/2/115", 16, "29th" );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_2_F_3" ), 6, 1, UnitSymbol.TRIANGLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_2_F_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BZ, Weapon.DE, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_2_F_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BR } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "G/2/115", 16, "29th" );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_2_G_3" ), 6, 1, UnitSymbol.CIRCLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_2_G_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.MO, Weapon.BG, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_2_G_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.MO } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "H/2/115", 16, "29th" );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_2_H_3" ), 7, 2, UnitSymbol.DIAMOND, USUnit.HEAVY_INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_2_H_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.MO, Weapon.DE, Weapon.MG } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_2_H_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.DE } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "I/3/115", 16, "29th" );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_3_I_3" ), 6, 1, UnitSymbol.TRIANGLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_3_I_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BZ, Weapon.BG, Weapon.RD } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_3_I_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.RD } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "K/3/115", 19, "29th" );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_3_K_3" ), 6, 1, UnitSymbol.CIRCLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_3_K_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BZ, Weapon.DE, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_3_K_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BZ } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "L/3/115", 19, "29th" );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_3_L_3" ), 6, 1, UnitSymbol.DIAMOND, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_3_L_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.MO, Weapon.BG, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_3_L_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BR } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "M/3/115", 19, "29th" );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_3_M_3" ), 7, 2, UnitSymbol.TRIANGLE, USUnit.HEAVY_INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_3_M_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.MO, Weapon.DE, Weapon.MG } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_3_M_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.MO } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.ANTI_TANK, "AT/115", 20, "29th" );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_AT_1" ), 2, 5, UnitSymbol.DIAMOND, USUnit.ANTI_TANK_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.HQ, "115", 19, "29th" );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_HQ" ), 0, 1, null, USUnit.HQ_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_115_HQ_CP" ), 0, 1, null, USUnit.HQ_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "A/1/116", 1, "DG4" );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_1_A_3" ), 6, 1, UnitSymbol.CIRCLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_1_A_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BZ, Weapon.BG, Weapon.RD } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_1_A_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BZ } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "B/1/116", 3, "DG2" );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_1_B_3" ), 6, 1, UnitSymbol.CIRCLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_1_B_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BZ, Weapon.DE, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_1_B_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.DE } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "C/1/116", 4, "DG1" );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_1_C_3" ), 6, 1, UnitSymbol.DIAMOND, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_1_C_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.MO, Weapon.BG, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_1_C_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BG } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "D/1/116", 4, "DG3" );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_1_D_3" ), 7, 2, UnitSymbol.TRIANGLE, USUnit.HEAVY_INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_1_D_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.MO, Weapon.DE, Weapon.MG } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_1_D_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.MG } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "E/2/116", 1, "EG3" );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_2_E_3" ), 6, 1, UnitSymbol.CIRCLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_2_E_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BZ, Weapon.BG, Weapon.RD } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_2_E_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BG } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "F/2/116", 1, "DR2" );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_2_F_3" ), 6, 1, UnitSymbol.TRIANGLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_2_F_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BZ, Weapon.DE, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_2_F_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BR } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "G/2/116", 1, "DR2" );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_2_G_3" ), 6, 1, UnitSymbol.DIAMOND, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_2_G_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.MO, Weapon.BG, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_2_G_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.MO } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "H/2/116", 3, "DR1" );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_2_H_3" ), 7, 2, UnitSymbol.DIAMOND, USUnit.HEAVY_INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_2_H_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.MO, Weapon.DE, Weapon.MG } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_2_H_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.DE } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "I/3/116", 4, "DR2" );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_3_I_3" ), 6, 1, UnitSymbol.TRIANGLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_3_I_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BZ, Weapon.BG, Weapon.RD } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_3_I_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.RD } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "K/3/116", 4, "DW2" );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_3_K_3" ), 6, 1, UnitSymbol.CIRCLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_3_K_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BZ, Weapon.DE, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_3_K_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BZ } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "L/3/116", 4, "DW2" );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_3_L_3" ), 6, 1, UnitSymbol.DIAMOND, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_3_L_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.MO, Weapon.BG, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_3_L_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BR } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "L/3/116", 4, "DW2" );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_3_L_3" ), 6, 1, UnitSymbol.DIAMOND, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_3_L_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.MO, Weapon.BG, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_3_L_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BR } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.INFANTRY, "M/3/116", 5, "DR" );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_3_M_3" ), 7, 2, UnitSymbol.TRIANGLE, USUnit.HEAVY_INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_3_M_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.MO, Weapon.DE, Weapon.MG } ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_3_M_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.MO } ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.ANTI_TANK, "AT/116", 9, "DR" );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_AT_1" ), 2, 5, UnitSymbol.DIAMOND, USUnit.ANTI_TANK_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.ARTILLERY_DUKW, "Can/116", 8, "DW" );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_Can_2" ), 3, -1, UnitSymbol.CIRCLE, USUnit.ARTILLERY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_Can_1" ), 1, -1, UnitSymbol.CIRCLE, USUnit.ARTILLERY_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.HQ, "116", 5, "DW" );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_HQ" ), 0, 1, null, USUnit.HQ_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_116_HQ_CP" ), 0, 1, null, USUnit.HQ_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.ANTI_AIRCRAFT, "3-4/397", 3, "EG3" );
        unit.addState( new UnitState( ImageFactory.get( "29th_397_3_4_2" ), 4, 3, UnitSymbol.DIAMOND, USUnit.ANTI_AIRCRAFT_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_397_3_4_1" ), 2, 3, UnitSymbol.DIAMOND, USUnit.ANTI_AIRCRAFT_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.ANTI_AIRCRAFT, "5-6/397", 3, "DW3" );
        unit.addState( new UnitState( ImageFactory.get( "29th_397_5_6_2" ), 4, 3, UnitSymbol.TRIANGLE, USUnit.ANTI_AIRCRAFT_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_397_5_6_1" ), 2, 3, UnitSymbol.TRIANGLE, USUnit.ANTI_AIRCRAFT_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.SELF_PROPELLED_ANTI_AIRCRAFT, "A-B/467", 9, "EG" );
        unit.addState( new UnitState( ImageFactory.get( "29th_467_A_B_2" ), 3, 3, UnitSymbol.CIRCLE, USUnit.ANTI_AIRCRAFT_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_467_A_B_1" ), 1, 3, UnitSymbol.CIRCLE, USUnit.ANTI_AIRCRAFT_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.SELF_PROPELLED_ANTI_AIRCRAFT, "C-D/467", 9, "DW" );
        unit.addState( new UnitState( ImageFactory.get( "29th_467_C_D_2" ), 3, 3, UnitSymbol.TRIANGLE, USUnit.ANTI_AIRCRAFT_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_467_C_D_1" ), 1, 3, UnitSymbol.TRIANGLE, USUnit.ANTI_AIRCRAFT_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.TANK, "1/A/743", 1, "DR3" );
        unit.addState( new UnitState( ImageFactory.get( "29th_743_A_1_2" ), 4, 5, UnitSymbol.DIAMOND, USUnit.TANK_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_743_A_1_1" ), 2, 5, UnitSymbol.DIAMOND, USUnit.TANK_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.TANK, "2/A/743", 1, "EG2" );
        unit.addState( new UnitState( ImageFactory.get( "29th_743_A_2_2" ), 4, 5, UnitSymbol.TRIANGLE, USUnit.TANK_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_743_A_2_1" ), 2, 5, UnitSymbol.TRIANGLE, USUnit.TANK_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.TANK, "1/B/743", 0, "DG2" );
        unit.addState( new UnitState( ImageFactory.get( "29th_743_B_1_2" ), 4, 5, UnitSymbol.TRIANGLE, USUnit.TANK_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_743_B_1_1" ), 2, 5, UnitSymbol.TRIANGLE, USUnit.TANK_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.TANK, "2/B/743", 0, "DG3" );
        unit.addState( new UnitState( ImageFactory.get( "29th_743_B_2_2" ), 4, 5, UnitSymbol.CIRCLE, USUnit.TANK_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_743_B_2_1" ), 2, 5, UnitSymbol.CIRCLE, USUnit.TANK_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.TANK, "1/C/743", 0, "DW1" );
        unit.addState( new UnitState( ImageFactory.get( "29th_743_C_1_2" ), 4, 5, UnitSymbol.DIAMOND, USUnit.TANK_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_743_C_1_1" ), 2, 5, UnitSymbol.DIAMOND, USUnit.TANK_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.TANK, "2/C/743", 0, "DW3" );
        unit.addState( new UnitState( ImageFactory.get( "29th_743_C_2_2" ), 4, 5, UnitSymbol.CIRCLE, USUnit.TANK_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_743_C_2_1" ), 2, 5, UnitSymbol.CIRCLE, USUnit.TANK_WEAPONS ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.ENGINEER, "20-121", -1, "" );
        unit.addState( new UnitState( ImageFactory.get( "29th_Eng_20_121" ), 0, 1, null, null ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.ENGINEER, "112-149", -1, "" );
        unit.addState( new UnitState( ImageFactory.get( "29th_Eng_112_149" ), 0, 1, null, null ) );
        usUnits.add( unit );

        unit = new USUnit( Division.US_29, UnitType.GENERAL, "Gen. Cota", 5, "DW" );
        unit.addState( new UnitState( ImageFactory.get( "29th_Gen_Cota" ), 0, 1, null, null ) );
        unit.addState( new UnitState( ImageFactory.get( "29th_Gen_Cota_r" ), 0, 1, null, null ) );
        usUnits.add( unit );

        return usUnits;
    }


    public static List<GermanUnit> loadGermanWNs() {
        List<GermanUnit> germanWNs = new ArrayList<>();
        // WN
        GermanUnit unit = new GermanUnit( UnitType.WN, "WN-1-BG" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_WN-1-BG" ), 1, 0, null, new Weapon[]{ Weapon.BG } ) );
        germanWNs.add( unit );

        unit = new GermanUnit( UnitType.WN, "WN-1-BR" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_WN-1-BR" ), 1, 0, null, new Weapon[]{ Weapon.BR } ) );
        germanWNs.add( unit );

        unit = new GermanUnit( UnitType.WN, "WN-1-BZ" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_WN-1-BZ" ), 1, 0, null, new Weapon[]{ Weapon.BZ } ) );
        germanWNs.add( unit );

        unit = new GermanUnit( UnitType.WN, "WN-1-MO-BR" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_WN-1-MO-BR" ), 1, 0, null, new Weapon[]{ Weapon.MO, Weapon.BR } ) );
        germanWNs.add( unit );

        unit = new GermanUnit( UnitType.WN, "WN-2-BG" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_WN-2-BG" ), 2, 0, null, new Weapon[]{ Weapon.BG } ) );
        germanWNs.add( unit );

        unit = new GermanUnit( UnitType.WN, "WN-2-BZ" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_WN-2-BZ" ), 2, 0, null, new Weapon[]{ Weapon.BZ } ) );
        germanWNs.add( unit );

        unit = new GermanUnit( UnitType.WN, "WN-2-BZ-BR" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_WN-2-BZ-BR" ), 2, 0, null, new Weapon[]{ Weapon.BZ, Weapon.BR } ) );
        germanWNs.add( unit );

        unit = new GermanUnit( UnitType.WN_ROCKET, "WN_1Ro_1-MO-BZ" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_WN_1Ro_1-MO-BZ" ), 1, 0, null, new Weapon[]{ Weapon.MO, Weapon.BZ } ) );
        germanWNs.add( unit );

        unit = new GermanUnit( UnitType.WN_ARTILLERY_75, "WN_75_1-MO-BZ" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_WN_75_1-MO-BZ" ), 1, 0, null, new Weapon[]{ Weapon.MO, Weapon.BZ } ) );
        germanWNs.add( unit );

        unit = new GermanUnit( UnitType.WN_ARTILLERY_75, "WN_75_2-BG-BZ" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_WN_75_2-BG-BZ" ), 2, 0, null, new Weapon[]{ Weapon.BG, Weapon.BZ } ) );
        germanWNs.add( unit );

        unit = new GermanUnit( UnitType.WN_ARTILLERY_75, "WN_75_2-BG-MG" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_WN_75_2-BG-MG" ), 2, 0, null, new Weapon[]{ Weapon.BG, Weapon.MG } ) );
        germanWNs.add( unit );

        unit = new GermanUnit( UnitType.WN_ARTILLERY_75, "WN_75_2-MO-MG" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_WN_75_2-MO-MG" ), 2, 0, null, new Weapon[]{ Weapon.MO, Weapon.MG } ) );
        germanWNs.add( unit );

        unit = new GermanUnit( UnitType.WN_ARTILLERY_75, "WN_75_3-BG-BR" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_WN_75_3-BG-BR" ), 3, 0, null, new Weapon[]{ Weapon.BG, Weapon.BR } ) );
        germanWNs.add( unit );

        unit = new GermanUnit( UnitType.WN_ARTILLERY_75, "WN_75_3-BG-BZ" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_WN_75_3-BG-BZ" ), 3, 0, null, new Weapon[]{ Weapon.BG, Weapon.BZ } ) );
        germanWNs.add( unit );

        unit = new GermanUnit( UnitType.WN_ARTILLERY_88, "WN_88_2-BG-MO" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_WN_88_2-BG-MO" ), 2, 0, null, new Weapon[]{ Weapon.BG, Weapon.MO } ) );
        germanWNs.add( unit );

        unit = new GermanUnit( UnitType.WN_ARTILLERY_88, "WN_88_2-BZ-MG" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_WN_88_2-BZ-MG" ), 2, 0, null, new Weapon[]{ Weapon.BZ, Weapon.MG } ) );
        germanWNs.add( unit );

        // TODO Added these two WNs since we need 18, but I don't know if these are the right ones to add - double check!
        unit = new GermanUnit( UnitType.WN, "WN-1-BR" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_WN-1-BR" ), 1, 0, null, new Weapon[]{ Weapon.BR } ) );
        germanWNs.add( unit );
        unit = new GermanUnit( UnitType.WN, "WN-1-BR" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_WN-1-BR" ), 1, 0, null, new Weapon[]{ Weapon.BR } ) );
        germanWNs.add( unit );

        return germanWNs;
    }


    public static List<GermanUnit> getGermanDivisionReinforcements() {
        List<GermanUnit> germanReinforcements = new ArrayList<>();
        // Reinforcements
        GermanUnit unit = new GermanUnit( UnitType.INFANTRY, "1P/352" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_D_352_1P" ), 3, 0, null, new Weapon[]{ Weapon.FL, Weapon.BR } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "1R/352" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_D_352_1R" ), 3, 0, null, new Weapon[]{ Weapon.FL } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "2P/352" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_D_352_2P" ), 3, 0, null, new Weapon[]{ Weapon.FL, Weapon.BZ } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "2R/352" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_D_352_2R" ), 3, 0, null, new Weapon[]{ Weapon.BZ } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "3P/352" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_D_352_3P" ), 3, 0, null, new Weapon[]{ Weapon.BZ, Weapon.BR } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "3R/352" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_D_352_3R" ), 3, 0, null, new Weapon[]{ Weapon.BR } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "2/1/726" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_D_726_1_2" ), 3, 0, null, new Weapon[]{ Weapon.BR } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "5/2/915" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_D_915_2_5" ), 4, 0, null, new Weapon[]{ Weapon.BZ, Weapon.FL } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "6/2/915" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_D_915_2_6" ), 4, 0, null, new Weapon[]{ Weapon.BZ, Weapon.FL } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "7/2/915" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_D_915_2_7" ), 4, 0, null, new Weapon[]{ Weapon.BR, Weapon.FL } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "8/2/915" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_D_915_2_8" ), 4, 0, null, new Weapon[]{ Weapon.BR, Weapon.BZ } ) );
        germanReinforcements.add( unit );

        return germanReinforcements;
    }


    public static List<GermanUnit> getGermanTacticalReinforcements() {
        List<GermanUnit> germanReinforcements = new ArrayList<>();
        // Tactical Reinforcements
        GermanUnit unit = new GermanUnit( UnitType.INFANTRY, "1/1/726" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_T_726_1_1" ), 1, 0, null, new Weapon[]{ Weapon.BR } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "3/1/726" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_T_726_1_3" ), 2, 0, null, new Weapon[]{ Weapon.BR } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "9/3/726" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_T_726_3_9" ), 3, 0, null, new Weapon[]{ Weapon.FL } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "10/3/726" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_T_726_3_10" ), 1, 0, null, new Weapon[]{ Weapon.BZ, Weapon.BR } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "11/3/726" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_T_726_3_11" ), 1, 0, null, new Weapon[]{ Weapon.BZ } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "5/2/916" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_T_916_2_5" ), 4, 0, null, new Weapon[]{ Weapon.FL, Weapon.BR } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "6/2/916" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_T_916_2_6" ), 2, 0, null, new Weapon[]{ Weapon.FL, Weapon.BZ } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "7/2/916" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_T_916_2_7" ), 4, 0, null, new Weapon[]{ Weapon.FL, Weapon.BZ } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "8/2/916" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_T_916_2_8" ), 3, 0, null, new Weapon[]{ Weapon.BR, Weapon.BZ } ) );
        germanReinforcements.add( unit );

        return germanReinforcements;
    }



    public static List<GermanUnit> getGermanKMReinforcements() {
        List<GermanUnit> germanReinforcements = new ArrayList<>();
        // Kampgruppe Meyer
        GermanUnit unit = new GermanUnit( UnitType.INFANTRY, "1/Fus/352" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_M_352_Fus_1" ), 3, 0, null, new Weapon[]{ Weapon.BR, Weapon.FL } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "2/Fus/352" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_M_352_Fus_2" ), 3, 0, null, new Weapon[]{ Weapon.FL } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "3/Fus/352" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_M_352_Fus_3" ), 3, 0, null, new Weapon[]{ Weapon.BZ, Weapon.BR } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "4/Fus/352" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_M_352_Fus_4" ), 3, 0, null, new Weapon[]{ Weapon.BZ } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "1/1/915" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_M_915_1_1" ), 4, 0, null, new Weapon[]{ Weapon.FL, Weapon.BZ } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "2/1/915" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_M_915_1_2" ), 4, 0, null, new Weapon[]{ Weapon.FL } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "3/1/915" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_M_915_1_3" ), 4, 0, null, new Weapon[]{ Weapon.FL, Weapon.BZ } ) );
        germanReinforcements.add( unit );

        unit = new GermanUnit( UnitType.INFANTRY, "4/1/915" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_M_915_1_4" ), 4, 0, null, new Weapon[]{ Weapon.BR } ) );
        germanReinforcements.add( unit );

        return germanReinforcements;
    }


    public static Map<DepthMarkerType, List<DepthMarker>> getDepthMarkers() {
        Map<DepthMarkerType, List<DepthMarker>> germanDepthMarkers = new HashMap<>();
        germanDepthMarkers.put( DepthMarkerType.WN, new ArrayList<>() );
        germanDepthMarkers.put( DepthMarkerType.MOBILE, new ArrayList<>() );
        germanDepthMarkers.put( DepthMarkerType.BUILDING, new ArrayList<>() );

        DepthMarker dm = new DepthMarker( ImageFactory.get( "Ger_Depth_1_AR" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 1, new Weapon[]{ Weapon.AR } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_1_DE" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 1, new Weapon[]{ Weapon.AR, Weapon.DE } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_1_AR_RD" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 1, new Weapon[]{ Weapon.AR, Weapon.RD } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_1_DE" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 1, new Weapon[]{ Weapon.DE } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_1_FL_MO" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 1, new Weapon[]{ Weapon.FL, Weapon.MO } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_1_FL_RD" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 1, new Weapon[]{ Weapon.FL, Weapon.RD } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_1_MG" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 1, new Weapon[]{ Weapon.MG } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_1_MG_MO" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 1, new Weapon[]{ Weapon.MG, Weapon.MO } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_1_MO" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 1, new Weapon[]{ Weapon.MO } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_1_NA" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 1, new Weapon[]{ Weapon.NA } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_1_RD" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 1, new Weapon[]{ Weapon.RD } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_2" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 2, new Weapon[]{} );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_2_AR" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 2, new Weapon[]{ Weapon.AR } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_2_AR_RD" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 2, new Weapon[]{ Weapon.AR, Weapon.RD } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_2_DE" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 2, new Weapon[]{ Weapon.DE } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_2_DE_FL" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 2, new Weapon[]{ Weapon.DE, Weapon.FL } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_2_FL" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 2, new Weapon[]{ Weapon.FL } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_2_FL_AR" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 2, new Weapon[]{ Weapon.FL, Weapon.AR } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_2_FL_DE" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 2, new Weapon[]{ Weapon.FL, Weapon.DE } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_2_MG" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 2, new Weapon[]{ Weapon.MG } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_2_MG_FL" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 2, new Weapon[]{ Weapon.MG, Weapon.FL } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_2_MG_MO" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 2, new Weapon[]{ Weapon.MG, Weapon.MO } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_2_NA" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 2, new Weapon[]{ Weapon.NA } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_2_RD" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 2, new Weapon[]{ Weapon.RD } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_2_RD_DE" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 2, new Weapon[]{ Weapon.RD, Weapon.DE } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_2_RD_FL" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 2, new Weapon[]{ Weapon.RD, Weapon.FL } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_2_RD_MO" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 2, new Weapon[]{ Weapon.RD, Weapon.MO } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_3_AR" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 3, new Weapon[]{ Weapon.AR } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_3_FL" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 3, new Weapon[]{ Weapon.FL } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_3_MG_FL" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 3, new Weapon[]{ Weapon.MG, Weapon.FL } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_3_NA" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 3, new Weapon[]{ Weapon.NA } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_3_RD_DE" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 3, new Weapon[]{ Weapon.RD, Weapon.DE } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_3_RD_MO" ), DepthMarkerType.WN, DepthMarkerEffect.STRENGTH, 3, new Weapon[]{ Weapon.RD, Weapon.MO } );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );
        dm = new DepthMarker( ImageFactory.get( "Ger_Depth_Tactical_Reinforce" ), DepthMarkerType.WN, DepthMarkerEffect.TACTICAL_REINFORCEMENT, 0, null );
        germanDepthMarkers.get( DepthMarkerType.WN ).add( dm );

        // TODO Complete this

        return germanDepthMarkers;
    }

    /*
        // German Artillery
        unit = new GermanUnit( UnitType.ARTILLERY_88, "1Flak" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Ari_1Flak" ), 0, 0, null, null ) );
        // TODO Place on appropriate spot on board

        unit = new GermanUnit( UnitType.ARTILLERY_88, "1Flak" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Ari_1Flak" ), 0, 0, null, null ) );
        // TODO Place on appropriate spot on board

        unit = new GermanUnit( UnitType.ARTILLERY_105, "1/1352" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Ari_1352_1" ), 0, 0, null, null ) );
        // TODO Place on appropriate spot on board

        unit = new GermanUnit( UnitType.ARTILLERY_105, "1/1352" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Ari_1352_1" ), 0, 0, null, null ) );
        // TODO Place on appropriate spot on board
    */
}
