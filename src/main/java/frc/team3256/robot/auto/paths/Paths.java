package frc.team3256.robot.auto.paths;

import frc.team3256.warriorlib.auto.purepursuit.Path;
import frc.team3256.warriorlib.auto.purepursuit.PathGenerator;
import frc.team3256.warriorlib.auto.purepursuit.PoseEstimator;
import frc.team3256.warriorlib.math.Vector;

import java.util.Arrays;
import java.util.List;

import static frc.team3256.robot.constants.DriveConstants.*;

public class Paths {
    private static List<Path> rightShootAutoPath;
    private static List<Path> rightBackShootAutoPath;
    private static List<Path> rightTrenchCollectAutoPath;
    private static List<Path> rightTrenchSixBallAutoPath;
    private static List<Path> rightTrenchCollectWallAutoPath;
    private static List<Path> rightTrenchCollectTenBallAutoPath;
    private static List<Path> stealTwoBallAutoPath;
    private static PoseEstimator poseEstimator = PoseEstimator.getInstance();

    private static List<Path> slalomPath;

    public static void initialize() {
        getRightShootAutoPath();
        getRightBackShootAutoPath();
        getRightTrenchCollectAutoPath();
        getRightTrenchSixBallAutoPath();
        getRightTrenchCollectWallAutoPath();
        getRightTrenchCollectTenBallAutoPath();
        getStealTwoBallAutoPath();
        getSlalomPath();
    }

    public static List<Path> getSlalomPath() {
        if (slalomPath != null)
            return slalomPath;

        PathGenerator s = new PathGenerator(spacing, true);
        s.addPoint(new Vector(0.0, 0.0));
        s.addPoint(new Vector(-0.052253612051174514, 6.074730441105459));
        s.addPoint(new Vector(-0.3980456567362296, 12.15527210870372));
        s.addPoint(new Vector(-1.2733107979896658, 18.138649011668534));
        s.addPoint(new Vector(-2.8466306716227905, 23.932731182526936));
        s.addPoint(new Vector(-5.215915615044622, 29.457955602541205));
        s.addPoint(new Vector(-8.407225364084297, 34.64886155234356));
        s.addPoint(new Vector(-12.228576815764626, 39.29777392859722));
        s.addPoint(new Vector(-16.600274470262576, 43.49146120040314));
        s.addPoint(new Vector(-21.35759296330187, 47.23654752161477));
        s.addPoint(new Vector(-26.4032927058813, 50.60789137200301));
        s.addPoint(new Vector(-31.62554104978274, 53.663186928990626));
        s.addPoint(new Vector(-36.95793936749928, 56.489126745194596));
        s.addPoint(new Vector(-42.38458101968308, 59.21436635651693));
        s.addPoint(new Vector(-47.758928762944976, 61.97751832794671));
        s.addPoint(new Vector(-52.90222385913319, 65.13533563537882));
        s.addPoint(new Vector(-57.62385688252621, 68.96451877267086));
        s.addPoint(new Vector(-61.94619023349169, 73.22700006407375));
        s.addPoint(new Vector(-65.79372013960584, 77.92247300881061));
        s.addPoint(new Vector(-69.25259897014463, 82.88576522228448));
        s.addPoint(new Vector(-72.37490845615551, 88.03035224063908));
        s.addPoint(new Vector(-75.1913092778367, 93.33753476974687));
        s.addPoint(new Vector(-77.7255866810698, 98.85668580657895));
        s.addPoint(new Vector(-79.92613418793633, 104.53274372197151));
        s.addPoint(new Vector(-81.7464268930253, 110.33607338683552));
        s.addPoint(new Vector(-83.13944434841228, 116.25370146105513));
        s.addPoint(new Vector(-84.04881043849824, 122.19182677071248));
        s.addPoint(new Vector(-84.46845962365232, 128.25846138468424));
        s.addPoint(new Vector(-84.4192068251583, 134.32163282778777));
        s.addPoint(new Vector(-84.05858157470533, 140.3299217550702));
        s.addPoint(new Vector(-83.54403151261884, 146.37063717401415));
        s.addPoint(new Vector(-82.60086802563448, 152.38775150987854));
        s.addPoint(new Vector(-81.11096974073271, 158.26890111242926));
        s.addPoint(new Vector(-79.12441173973465, 164.02064211862992));
        s.addPoint(new Vector(-76.74065713108976, 169.62089064624038));
        s.addPoint(new Vector(-74.08017813261317, 175.00228291520935));
        s.addPoint(new Vector(-71.14821315955218, 180.28910803116437));
        s.addPoint(new Vector(-68.0032448130863, 185.450186591748));
        s.addPoint(new Vector(-64.6942098310154, 190.45759695569333));
        s.addPoint(new Vector(-61.185640417118975, 195.38752476732705));
        s.addPoint(new Vector(-57.47911936759924, 200.23053052832702));
        s.addPoint(new Vector(-53.633511349270435, 204.88733029319553));
        s.addPoint(new Vector(-49.593319595526, 209.3707697681815));
        s.addPoint(new Vector(-45.29771538114366, 213.6162924989085));
        s.addPoint(new Vector(-40.6352087362487, 217.4111578131565));
        s.addPoint(new Vector(-35.16437389557285, 219.98957601186132));
        s.addPoint(new Vector(-29.16133837947585, 220.7559504607461));
        s.addPoint(new Vector(-23.545065328149377, 222.9134107409823));
        s.addPoint(new Vector(-18.349032930425892, 226.12846369299328));
        s.addPoint(new Vector(-13.671239360363273, 229.89767239091984));
        s.addPoint(new Vector(-9.398831536113192, 234.1747701999503));
        s.addPoint(new Vector(-5.65793319372105, 238.88421914501865));
        s.addPoint(new Vector(-2.562078768246039, 244.09621778509316));
        s.addPoint(new Vector(-0.3833776384475982, 249.79154355541093));
        s.addPoint(new Vector(0.6384719800880703, 255.78092198244704));
        s.addPoint(new Vector(0.7773262665063214, 261.8318448193304));
        s.addPoint(new Vector(0.4477680229365433, 267.8866582565594));
        s.addPoint(new Vector(-0.9809659426169617, 273.7731267762688));
        s.addPoint(new Vector(-3.58922443206464, 279.1846661589801));
        s.addPoint(new Vector(-7.118423997319695, 284.0493335614125));
        s.addPoint(new Vector(-11.345592727248857, 288.38788636137315));
        s.addPoint(new Vector(-16.085260613512787, 292.1572507387125));
        s.addPoint(new Vector(-21.251627494130275, 295.2745745313224));
        s.addPoint(new Vector(-26.8867134538157, 297.4774634349385));
        s.addPoint(new Vector(-32.941741576888774, 298.03197694366236));
        s.addPoint(new Vector(-38.922459088848086, 297.35047692974956));
        s.addPoint(new Vector(-44.762811247480855, 295.6246192742667));
        s.addPoint(new Vector(-50.27695586476227, 293.25060966552394));
        s.addPoint(new Vector(-55.515628879096624, 290.32427610464697));
        s.addPoint(new Vector(-60.4355038800183, 286.81579440522216));
        s.addPoint(new Vector(-64.83431616469228, 282.7061995315353));
        s.addPoint(new Vector(-68.49396883946471, 277.9186853809184));
        s.addPoint(new Vector(-71.10168136788215, 272.4693965806881));
        s.addPoint(new Vector(-72.46417997269555, 266.5935870937931));
        s.addPoint(new Vector(-72.86488129491536, 260.565139420424));
        s.addPoint(new Vector(-72.85364325492209, 254.4999345459465));
        s.addPoint(new Vector(-71.81170778493885, 248.5451068129636));
        s.addPoint(new Vector(-69.32283492669254, 243.0133355630958));
        s.addPoint(new Vector(-65.74511889023304, 238.1750303738523));
        s.addPoint(new Vector(-61.42182769363164, 233.88882779628122));
        s.addPoint(new Vector(-56.703677526149676, 230.12483234731602));
        s.addPoint(new Vector(-51.68738362872347, 226.7328239172172));
        s.addPoint(new Vector(-46.45229711912708, 223.64310817305304));
        s.addPoint(new Vector(-41.137089218880476, 220.85641420918475));
        s.addPoint(new Vector(-35.65198278859235, 218.28108237105133));
        s.addPoint(new Vector(-30.09326112465206, 215.94648936606708));
        s.addPoint(new Vector(-24.46529599556527, 213.85519496646307));
        s.addPoint(new Vector(-18.693401305756282, 211.98097929938544));
        s.addPoint(new Vector(-13.049035909397304, 209.88201129519902));
        s.addPoint(new Vector(-8.382125876689571, 205.95056952153809));
        s.addPoint(new Vector(-5.156555960721477, 200.83404793195064));
        s.addPoint(new Vector(-2.8227424632119096, 195.23312043327502));
        s.addPoint(new Vector(-1.058175313048224, 189.47946940266527));
        s.addPoint(new Vector(0.3466531174578904, 183.57939379770724));
        s.addPoint(new Vector(1.4767133430091235, 177.62039337109167));
        s.addPoint(new Vector(2.3915659283586024, 171.62203210387474));
        s.addPoint(new Vector(3.1285337543533274, 165.58610984458292));
        s.addPoint(new Vector(3.7077198803274314, 159.53800946583672));
        s.addPoint(new Vector(4.139979522519269, 153.5466308819958));
        s.addPoint(new Vector(4.446253734261859, 147.55242046553352));
        s.addPoint(new Vector(4.664855151392629, 141.4877811895288));
        s.addPoint(new Vector(4.839605805878477, 135.4405762768552));
        s.addPoint(new Vector(4.89890705582485, 129.41125346747475));
        s.addPoint(new Vector(4.799265557135556, 123.37500138044118));
        s.addPoint(new Vector(4.532238035426047, 117.35827433759295));
        s.addPoint(new Vector(4.088642417701806, 111.31499501813434));
        s.addPoint(new Vector(3.462701717031422, 105.34643422257977));
        s.addPoint(new Vector(2.620018338949592, 99.37740195020544));
        s.addPoint(new Vector(1.5144640372997742, 93.44642420835905));
        s.addPoint(new Vector(0.06344850386599887, 87.58431488850451));
        s.addPoint(new Vector(-1.8862693056703392, 81.82955539083952));
        s.addPoint(new Vector(-4.516248655859243, 76.42229234927508));
        s.addPoint(new Vector(-7.930458830993871, 71.47544626616533));
        /*s.addPoint(new Vector(-11.739864140668175, 66.77673402936435));
        s.addPoint(new Vector(-15.907589246381065, 62.424168722321326));
        s.addPoint(new Vector(-20.26377252032364, 58.29279894997819));
        s.addPoint(new Vector(-24.785229286713474, 54.20566198842073));
        s.addPoint(new Vector(-29.32344852101042, 50.19831134479635));
        s.addPoint(new Vector(-33.875454199009496, 46.21255153847173));
        s.addPoint(new Vector(-38.43611985628314, 42.20850194399564));
        s.addPoint(new Vector(-42.93821187357052, 38.20384889026545));
        s.addPoint(new Vector(-47.38884277141936, 34.14418122690465));
        s.addPoint(new Vector(-51.73948058823689, 30.004130901781934));
        s.addPoint(new Vector(-55.95338726460268, 25.693630938132173));
        s.addPoint(new Vector(-59.85168756511251, 21.117612969059607));
        s.addPoint(new Vector(-62.99280586885641, 15.991474675205147));
        s.addPoint(new Vector(-64.88154333126005, 10.248361816192244));
        s.addPoint(new Vector(-66.32308530393065, 4.420028340904267));
        s.addPoint(new Vector(-67.26439557898799, -1.5515649198678219));
        s.addPoint(new Vector(-67.7713278863414, -7.593469888103755));
        s.addPoint(new Vector(-67.99564290284559, -13.695325333818857));
        s.addPoint(new Vector(-68.21793728127354, -19.713168187744436));
        s.addPoint(new Vector(-68.21793728127354, -19.713168187744436));*/

        s.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        s.setVelocities(maxVel, maxAccel, maxVelk);

        Path sPath = s.generatePath();

        slalomPath = Arrays.asList(sPath);
        return slalomPath;
    }

    public static List<Path> getStealTwoBallAutoPath() {
        if (stealTwoBallAutoPath!= null)
            return stealTwoBallAutoPath;
        PathGenerator firstSegment = new PathGenerator(spacing, true);

        firstSegment.addPoint(new Vector(0,0));
        firstSegment.addPoint(new Vector(0,104)); //35.5 in robot length

        firstSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        firstSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path firstSegmentPath = firstSegment.generatePath();

        PathGenerator secondSegment = new PathGenerator(spacing, true);

        secondSegment.addPoint(new Vector(0,104));
        secondSegment.addPoint(new Vector(0,0));

        secondSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        secondSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path secondSegmentPath = secondSegment.generatePath();

        stealTwoBallAutoPath = Arrays.asList(firstSegmentPath, secondSegmentPath);
        return stealTwoBallAutoPath;
    }

    public static List<Path> getRightShootAutoPath() {
        if (rightShootAutoPath != null)
            return rightShootAutoPath;
        PathGenerator firstSegment = new PathGenerator(spacing, true);

        firstSegment.addPoint(new Vector(0,0));
        firstSegment.addPoint(new Vector(0,30));

        firstSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        firstSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path firstSegmentPath = firstSegment.generatePath();

        rightShootAutoPath = Arrays.asList(firstSegmentPath);
        return rightShootAutoPath;
    }

    public static List<Path> getRightBackShootAutoPath() {
        if (rightBackShootAutoPath != null)
            return rightBackShootAutoPath;
        PathGenerator firstSegment = new PathGenerator(spacing, false);

        firstSegment.addPoint(new Vector(0,0));
        firstSegment.addPoint(new Vector(0,-30));

        firstSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        firstSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path firstSegmentPath = firstSegment.generatePath();

        rightBackShootAutoPath = Arrays.asList(firstSegmentPath);
        return rightBackShootAutoPath;
    }

    public static List<Path> getRightTrenchCollectAutoPath() {
        if (rightTrenchCollectAutoPath != null)
            return rightTrenchCollectAutoPath;

        //First Segment (Forward Spline)

        PathGenerator firstSegment = new PathGenerator(spacing, true);

        firstSegment.addPoint(new Vector(0,0));
        firstSegment.addPoint(new Vector(0,18));
        firstSegment.addPoint(new Vector(-0.05, 18.61));
        firstSegment.addPoint(new Vector(-1.52, 21.37));
        firstSegment.addPoint(new Vector(-2.49, 22.51));
        firstSegment.addPoint(new Vector(-5.31, 25.35));
        firstSegment.addPoint(new Vector(-7.24, 27.12));
        firstSegment.addPoint(new Vector(-9, 28.66));
        firstSegment.addPoint(new Vector(-10.4,29.82));
        firstSegment.addPoint(new Vector(-10.99, 30.40));
        firstSegment.addPoint(new Vector(-11.88, 31.15));
        firstSegment.addPoint(new Vector(-13.34, 32.38));
        firstSegment.addPoint(new Vector(-14.66, 33.47));
        firstSegment.addPoint(new Vector(-15.77, 34.39));
        firstSegment.addPoint(new Vector(-16.55, 35.03));
        firstSegment.addPoint(new Vector(-18.05, 36.25));
        firstSegment.addPoint(new Vector(-19.32, 37.28));
        firstSegment.addPoint(new Vector(-21.28, 38.86));
        firstSegment.addPoint(new Vector(-21.91, 39.37));
        firstSegment.addPoint(new Vector(-23.57, 40.7));
        firstSegment.addPoint(new Vector(-28.92, 44.98));
        firstSegment.addPoint(new Vector(-31.69, 47.22));
        firstSegment.addPoint(new Vector(-35,50));
        firstSegment.addPoint(new Vector(-37.62,52.27));
        firstSegment.addPoint(new Vector(-35,50));
        firstSegment.addPoint(new Vector(-37.31,52));
        firstSegment.addPoint(new Vector(-39.95,54.37));
        firstSegment.addPoint(new Vector(-41.10,55.42));
        firstSegment.addPoint(new Vector(-45.38, 59.51));
        firstSegment.addPoint(new Vector(-46.18,60.31));
        firstSegment.addPoint(new Vector(-46.91,61.04));
        firstSegment.addPoint(new Vector(-49.43,63.61));
        firstSegment.addPoint(new Vector(-51.66,65.98));
        firstSegment.addPoint(new Vector(-53.86,68.41));
        firstSegment.addPoint(new Vector(-55.51,70.31));
        firstSegment.addPoint(new Vector(-58.06,73.38));
        firstSegment.addPoint(new Vector(-59.79,75.61));
        firstSegment.addPoint(new Vector(-61,78.01));
        firstSegment.addPoint(new Vector(-64.09,82.01));
        firstSegment.addPoint(new Vector(-66.05,85.84));
        firstSegment.addPoint(new Vector(-67.46,90.03));
        firstSegment.addPoint(new Vector(-71.5, 96));
        firstSegment.addPoint(new Vector(-71.5, 100));
        firstSegment.addPoint(new Vector(-71.5, 110));
        firstSegment.addPoint(new Vector(-71.5, 120));
        firstSegment.addPoint(new Vector(-71.5, 130));
        firstSegment.addPoint(new Vector(-71.5, 140));
        firstSegment.addPoint(new Vector(-71.5, 150));
        firstSegment.addPoint(new Vector(-71.5, 160));
        firstSegment.addPoint(new Vector(-71.5, 170));
        firstSegment.addPoint(new Vector(-71.5, 237)); //174

        firstSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        firstSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path firstSegmentPath = firstSegment.generatePath();

        //Third Segment (Backwards Spline)

        PathGenerator secondSegment = new PathGenerator(spacing, false);

        secondSegment.addPoint(new Vector(-71.5, 237));
        secondSegment.addPoint(new Vector(-71.5, 170));
        secondSegment.addPoint(new Vector(-71.5, 160));
        secondSegment.addPoint(new Vector(-71.5, 150));
        secondSegment.addPoint(new Vector(-71.5, 140));
        secondSegment.addPoint(new Vector(-71.5, 130));
        secondSegment.addPoint(new Vector(-71.5, 120));
        secondSegment.addPoint(new Vector(-71.5, 110));
        secondSegment.addPoint(new Vector(-71.5, 100));
        secondSegment.addPoint(new Vector(-71.5, 96));
        secondSegment.addPoint(new Vector(-67.46,90.03));
        secondSegment.addPoint(new Vector(-66.05,85.84));
        secondSegment.addPoint(new Vector(-64.09,82.01));
        secondSegment.addPoint(new Vector(-61,78.01));
        secondSegment.addPoint(new Vector(-59.79,75.61));
        secondSegment.addPoint(new Vector(-58.06,73.38));
        secondSegment.addPoint(new Vector(-55.51,70.31));
        secondSegment.addPoint(new Vector(-53.86,68.41));
        secondSegment.addPoint(new Vector(-51.66,65.98));
        secondSegment.addPoint(new Vector(-49.43,63.61));
        secondSegment.addPoint(new Vector(-46.91,61.04));
        secondSegment.addPoint(new Vector(-46.18,60.31));
        secondSegment.addPoint(new Vector(-45.38, 59.51));
        secondSegment.addPoint(new Vector(-41.10,55.42));
        secondSegment.addPoint(new Vector(-39.95,54.37));
        secondSegment.addPoint(new Vector(-37.31,52));
        secondSegment.addPoint(new Vector(-35,50));
        secondSegment.addPoint(new Vector(-37.62,52.27));
        secondSegment.addPoint(new Vector(-35,50));
        secondSegment.addPoint(new Vector(-31.69, 47.22));
        secondSegment.addPoint(new Vector(-28.92, 44.98));
        secondSegment.addPoint(new Vector(-23.57, 40.7));
        secondSegment.addPoint(new Vector(-21.91, 39.37));
        secondSegment.addPoint(new Vector(-21.28, 38.86));
        secondSegment.addPoint(new Vector(-19.32, 37.28));
        secondSegment.addPoint(new Vector(-18.05, 36.25));
        secondSegment.addPoint(new Vector(-16.55, 35.03));
        secondSegment.addPoint(new Vector(-15.77, 34.39));
        secondSegment.addPoint(new Vector(-14.66, 33.47));
        secondSegment.addPoint(new Vector(-13.34, 32.38));
        secondSegment.addPoint(new Vector(-11.88, 31.15));
        secondSegment.addPoint(new Vector(-10.99, 30.40));
        secondSegment.addPoint(new Vector(-10.4,29.82));
        secondSegment.addPoint(new Vector(-9, 28.66));
        secondSegment.addPoint(new Vector(-7.24, 27.12));
        secondSegment.addPoint(new Vector(-5.31, 25.35));
        secondSegment.addPoint(new Vector(-2.49, 22.51));
        secondSegment.addPoint(new Vector(-1.52, 21.37));
        secondSegment.addPoint(new Vector(-0.05, 18.61));
        secondSegment.addPoint(new Vector(0, 18));

        secondSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        secondSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path secondSegmentPath = secondSegment.generatePath();

        rightTrenchCollectAutoPath = Arrays.asList(firstSegmentPath, secondSegmentPath);
        return rightTrenchCollectAutoPath;
    }

    public static List<Path> getRightTrenchSixBallAutoPath() {
        if (rightTrenchSixBallAutoPath != null)
            return rightTrenchSixBallAutoPath;

        //First Segment (Forward Spline)

        PathGenerator firstSegment = new PathGenerator(spacing, true);

        firstSegment.addPoint(new Vector(0,0));
        firstSegment.addPoint(new Vector(0,18));
        firstSegment.addPoint(new Vector(-0.05, 18.61));
        firstSegment.addPoint(new Vector(-1.52, 21.37));
        firstSegment.addPoint(new Vector(-2.49, 22.51));
        firstSegment.addPoint(new Vector(-5.31, 25.35));
        firstSegment.addPoint(new Vector(-7.24, 27.12));
        firstSegment.addPoint(new Vector(-9, 28.66));
        firstSegment.addPoint(new Vector(-10.4,29.82));
        firstSegment.addPoint(new Vector(-10.99, 30.40));
        firstSegment.addPoint(new Vector(-11.88, 31.15));
        firstSegment.addPoint(new Vector(-13.34, 32.38));
        firstSegment.addPoint(new Vector(-14.66, 33.47));
        firstSegment.addPoint(new Vector(-15.77, 34.39));
        firstSegment.addPoint(new Vector(-16.55, 35.03));
        firstSegment.addPoint(new Vector(-18.05, 36.25));
        firstSegment.addPoint(new Vector(-19.32, 37.28));
        firstSegment.addPoint(new Vector(-21.28, 38.86));
        firstSegment.addPoint(new Vector(-21.91, 39.37));
        firstSegment.addPoint(new Vector(-23.57, 40.7));
        firstSegment.addPoint(new Vector(-28.92, 44.98));
        firstSegment.addPoint(new Vector(-31.69, 47.22));
        firstSegment.addPoint(new Vector(-35,50));
        firstSegment.addPoint(new Vector(-37.62,52.27));
        firstSegment.addPoint(new Vector(-35,50));
        firstSegment.addPoint(new Vector(-37.31,52));
        firstSegment.addPoint(new Vector(-39.95,54.37));
        firstSegment.addPoint(new Vector(-41.10,55.42));
        firstSegment.addPoint(new Vector(-45.38, 59.51));
        firstSegment.addPoint(new Vector(-46.18,60.31));
        firstSegment.addPoint(new Vector(-46.91,61.04));
        firstSegment.addPoint(new Vector(-49.43,63.61));
        firstSegment.addPoint(new Vector(-51.66,65.98));
        firstSegment.addPoint(new Vector(-53.86,68.41));
        firstSegment.addPoint(new Vector(-55.51,70.31));
//        firstSegment.addPoint(new Vector(-58.06,73.38));
//        firstSegment.addPoint(new Vector(-59.79,75.61));
//        firstSegment.addPoint(new Vector(-61,78.01));
//        firstSegment.addPoint(new Vector(-64.09,82.01));
//        firstSegment.addPoint(new Vector(-66.05,85.84));
//        firstSegment.addPoint(new Vector(-67.46,90.03));
        firstSegment.addPoint(new Vector(-61, 96)); //-71.5
        firstSegment.addPoint(new Vector(-61, 100)); //-71.5
        firstSegment.addPoint(new Vector(-61, 110)); //-71.5
        firstSegment.addPoint(new Vector(-63, 120)); //-71.5
        firstSegment.addPoint(new Vector(-63, 130)); //-71.5
        firstSegment.addPoint(new Vector(-63, 140)); //-71.5
        firstSegment.addPoint(new Vector(-63, 150)); //-71.5
        firstSegment.addPoint(new Vector(-63, 160)); //-71.5
        firstSegment.addPoint(new Vector(-63, 167)); //-71.5

        firstSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        firstSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path firstSegmentPath = firstSegment.generatePath();

        //Second Segment (Backwards Spline)

        PathGenerator secondSegment = new PathGenerator(spacing, false);

        secondSegment.addPoint(new Vector(-63, 167));
        secondSegment.addPoint(new Vector(-63, 160));
        secondSegment.addPoint(new Vector(-63, 150));
        secondSegment.addPoint(new Vector(-63, 140));
        secondSegment.addPoint(new Vector(-63, 130));
        secondSegment.addPoint(new Vector(-63, 120));
        secondSegment.addPoint(new Vector(-61, 110));
        secondSegment.addPoint(new Vector(-61, 100));
        secondSegment.addPoint(new Vector(-61, 96));
//        secondSegment.addPoint(new Vector(-67.46,90.03));
//        secondSegment.addPoint(new Vector(-66.05,85.84));
//        secondSegment.addPoint(new Vector(-64.09,82.01));
//        secondSegment.addPoint(new Vector(-61,78.01));
//        secondSegment.addPoint(new Vector(-59.79,75.61));
//        secondSegment.addPoint(new Vector(-58.06,73.38));
        secondSegment.addPoint(new Vector(-55.51,70.31));
        secondSegment.addPoint(new Vector(-53.86,68.41));
        secondSegment.addPoint(new Vector(-51.66,65.98));
        secondSegment.addPoint(new Vector(-49.43,63.61));
        secondSegment.addPoint(new Vector(-46.91,61.04));
        secondSegment.addPoint(new Vector(-46.18,60.31));
        secondSegment.addPoint(new Vector(-45.38, 59.51));
        secondSegment.addPoint(new Vector(-41.10,55.42));
        secondSegment.addPoint(new Vector(-39.95,54.37));
        secondSegment.addPoint(new Vector(-37.31,52));
        secondSegment.addPoint(new Vector(-35,50));
        secondSegment.addPoint(new Vector(-37.62,52.27));
        secondSegment.addPoint(new Vector(-35,50));
        secondSegment.addPoint(new Vector(-31.69, 47.22));
        secondSegment.addPoint(new Vector(-28.92, 44.98));
        secondSegment.addPoint(new Vector(-23.57, 40.7));
        secondSegment.addPoint(new Vector(-21.91, 39.37));
        secondSegment.addPoint(new Vector(-21.28, 38.86));
        secondSegment.addPoint(new Vector(-19.32, 37.28));
        secondSegment.addPoint(new Vector(-18.05, 36.25));
        secondSegment.addPoint(new Vector(-16.55, 35.03));
        secondSegment.addPoint(new Vector(-15.77, 34.39));
        secondSegment.addPoint(new Vector(-14.66, 33.47));
        secondSegment.addPoint(new Vector(-13.34, 32.38));
        secondSegment.addPoint(new Vector(-11.88, 31.15));
        secondSegment.addPoint(new Vector(-10.99, 30.40));
        secondSegment.addPoint(new Vector(-10.4,29.82));
        secondSegment.addPoint(new Vector(-9, 28.66));
        secondSegment.addPoint(new Vector(-7.24, 27.12));
        secondSegment.addPoint(new Vector(-5.31, 25.35));
        secondSegment.addPoint(new Vector(-2.49, 22.51));
        secondSegment.addPoint(new Vector(-1.52, 21.37));
        secondSegment.addPoint(new Vector(-0.05, 18.61));
        secondSegment.addPoint(new Vector(0, 18));

        secondSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        secondSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path secondSegmentPath = secondSegment.generatePath();

        rightTrenchSixBallAutoPath = Arrays.asList(firstSegmentPath, secondSegmentPath);
        return rightTrenchSixBallAutoPath;
    }

    public static List<Path> getRightTrenchCollectWallAutoPath() {
        if (rightTrenchCollectWallAutoPath != null)
            return rightTrenchCollectWallAutoPath;

        //First Segment (Forward Spline)

        PathGenerator firstSegment = new PathGenerator(spacing, true);

        Vector offset = new Vector(75, 0);

        firstSegment.addPoint(new Vector(0, 0));
        firstSegment.addPoint(new Vector(0, 20));
        firstSegment.addPoint(new Vector(0, 40));
        firstSegment.addPoint(new Vector(0, 60));
        firstSegment.addPoint(new Vector(0, 80));
        firstSegment.addPoint(new Vector(0, 96));
        firstSegment.addPoint(new Vector(0, 100));
        firstSegment.addPoint(new Vector(0, 110));
        firstSegment.addPoint(new Vector(0, 120));
        firstSegment.addPoint(new Vector(0, 130));
        firstSegment.addPoint(new Vector(0, 140));
        firstSegment.addPoint(new Vector(0, 150));
        firstSegment.addPoint(new Vector(0, 160));
        firstSegment.addPoint(new Vector(0,167));
//        firstSegment.addPoint(new Vector(0, 170));
//        firstSegment.addPoint(new Vector(0, 237));

        firstSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        firstSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path firstSegmentPath = firstSegment.generatePath();

        //Third Segment (Backwards Spline)

        PathGenerator secondSegment = new PathGenerator(spacing, false);

        secondSegment.addPoint(new Vector(0, 167));
        secondSegment.addPoint(new Vector(0, 150));
        secondSegment.addPoint(new Vector(0, 140));
        secondSegment.addPoint(new Vector(0, 130));
        secondSegment.addPoint(new Vector(0, 120));
        secondSegment.addPoint(new Vector(0, 110));
        secondSegment.addPoint(new Vector(0, 100));
        secondSegment.addPoint(new Vector(0, 90));
        secondSegment.addPoint(new Vector(0, 80));
        secondSegment.addPoint(new Vector(0, 70));
        secondSegment.addPoint(new Vector(0, 60));
        secondSegment.addPoint(new Vector(0, 40));
        secondSegment.addPoint(new Vector(0, 20));
        secondSegment.addPoint(new Vector(0, 10));

//        secondSegment.addPoint(new Vector(-71.5, 237).add(offset));
//        secondSegment.addPoint(new Vector(-71.5, 170).add(offset));
//        secondSegment.addPoint(new Vector(-71.5, 160).add(offset));
//        secondSegment.addPoint(new Vector(-71.5, 150).add(offset));
//        secondSegment.addPoint(new Vector(-71.5, 140).add(offset));
//        secondSegment.addPoint(new Vector(-71.5, 130).add(offset));
//        secondSegment.addPoint(new Vector(-71.5, 120).add(offset));
//        secondSegment.addPoint(new Vector(-71.5, 110).add(offset));
//        secondSegment.addPoint(new Vector(-71.5, 100).add(offset));
//        secondSegment.addPoint(new Vector(-71.5, 96).add(offset));
//        secondSegment.addPoint(new Vector(-67.46,90.03).add(offset));
//        secondSegment.addPoint(new Vector(-66.05,85.84).add(offset));
//        secondSegment.addPoint(new Vector(-64.09,82.01).add(offset));
//        secondSegment.addPoint(new Vector(-61,78.01).add(offset));
//        secondSegment.addPoint(new Vector(-59.79,75.61).add(offset));
//        secondSegment.addPoint(new Vector(-58.06,73.38).add(offset));
//        secondSegment.addPoint(new Vector(-55.51,70.31).add(offset));
//        secondSegment.addPoint(new Vector(-53.86,68.41).add(offset));
//        secondSegment.addPoint(new Vector(-51.66,65.98).add(offset));
//        secondSegment.addPoint(new Vector(-49.43,63.61).add(offset));
//        secondSegment.addPoint(new Vector(-46.91,61.04).add(offset));
//        secondSegment.addPoint(new Vector(-46.18,60.31).add(offset));
//        secondSegment.addPoint(new Vector(-45.38, 59.51).add(offset));
//        secondSegment.addPoint(new Vector(-41.10,55.42).add(offset));
//        secondSegment.addPoint(new Vector(-39.95,54.37).add(offset));
//        secondSegment.addPoint(new Vector(-37.31,52).add(offset));
//        secondSegment.addPoint(new Vector(-35,50).add(offset));
//        secondSegment.addPoint(new Vector(-37.62,52.27).add(offset));
//        secondSegment.addPoint(new Vector(-35,50).add(offset));
//        secondSegment.addPoint(new Vector(-31.69, 47.22).add(offset));
//        secondSegment.addPoint(new Vector(-28.92, 44.98).add(offset));
//        secondSegment.addPoint(new Vector(-23.57, 40.7).add(offset));
//        secondSegment.addPoint(new Vector(-21.91, 39.37).add(offset));
//        secondSegment.addPoint(new Vector(-21.28, 38.86).add(offset));
//        secondSegment.addPoint(new Vector(-19.32, 37.28).add(offset));
//        secondSegment.addPoint(new Vector(-18.05, 36.25).add(offset));
//        secondSegment.addPoint(new Vector(-16.55, 35.03).add(offset));
//        secondSegment.addPoint(new Vector(-15.77, 34.39).add(offset));
//        secondSegment.addPoint(new Vector(-14.66, 33.47).add(offset));
//        secondSegment.addPoint(new Vector(-13.34, 32.38).add(offset));
//        secondSegment.addPoint(new Vector(-11.88, 31.15).add(offset));
//        secondSegment.addPoint(new Vector(-10.99, 30.40).add(offset));
//        secondSegment.addPoint(new Vector(-10.4,29.82).add(offset));
//        secondSegment.addPoint(new Vector(-9, 28.66).add(offset));
//        secondSegment.addPoint(new Vector(-7.24, 27.12).add(offset));
//        secondSegment.addPoint(new Vector(-5.31, 25.35).add(offset));
//        secondSegment.addPoint(new Vector(-2.49, 22.51).add(offset));
//        secondSegment.addPoint(new Vector(-1.52, 21.37).add(offset));
//        secondSegment.addPoint(new Vector(-0.05, 18.61).add(offset));
//        secondSegment.addPoint(new Vector(0, 18).add(offset));

        secondSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        secondSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path secondSegmentPath = secondSegment.generatePath();

        rightTrenchCollectWallAutoPath = Arrays.asList(firstSegmentPath, secondSegmentPath);
        return rightTrenchCollectWallAutoPath;
    }

    public static List<Path> getRightTrenchCollectTenBallAutoPath() {
        if (rightTrenchCollectTenBallAutoPath != null)
            return rightTrenchCollectTenBallAutoPath;

        //First Segment (Forward to Intake Two Balls)

        PathGenerator firstSegment = new PathGenerator(spacing, true);

        firstSegment.addPoint(new Vector(0,0));
        firstSegment.addPoint(new Vector(-0.00997438101811099,4.109449873323854));
        firstSegment.addPoint(new Vector(-0.07221798706818561,8.142432941288604));
        firstSegment.addPoint(new Vector(-0.22663426507743623,12.245540703187032));
        firstSegment.addPoint(new Vector(-0.4922460541405087,16.313403405380797));
        firstSegment.addPoint(new Vector(-0.8756010986213738,20.347441223216123));
        firstSegment.addPoint(new Vector(-1.3735342568415234,24.341291513736167));
        firstSegment.addPoint(new Vector(-1.9871308240016958,28.349751494710034));
        firstSegment.addPoint(new Vector(-2.6946502802075827,32.29401648786302));
        firstSegment.addPoint(new Vector(-3.49316042768956   ,36.22561258542538));
        firstSegment.addPoint(new Vector(-4.368284946800898,40.129357068477844));
        firstSegment.addPoint(new Vector(-5.321459633874511,44.05829034560722));
        firstSegment.addPoint(new Vector(-7.386240243802803,51.85685480472273));
        firstSegment.addPoint(new Vector(-8.483850292598945,55.75950828845703));
        firstSegment.addPoint(new Vector(-9.595308353933135,59.61565853503009));
        firstSegment.addPoint(new Vector(-10.720902302507042,63.47119439151038));
        firstSegment.addPoint(new Vector(-11.858003756518372,67.36500946763454));
        firstSegment.addPoint(new Vector(-12.980033085037917,71.26350904779639));
        firstSegment.addPoint(new Vector(-14.072061068887905,75.18801634654949));
        firstSegment.addPoint(new Vector(-15.093200088128214,79.08766607570999));
        firstSegment.addPoint(new Vector(-16.03048652380278,83.06131703509911));
        firstSegment.addPoint(new Vector(-16.80323438639374,87.00796759082237));
        firstSegment.addPoint(new Vector(-17.333455599979047,90.9812027947782));
        firstSegment.addPoint(new Vector(-17.45600303702753,94.99983276452679));
        firstSegment.addPoint(new Vector(-16.828469529082895,99.01563692990226));
        firstSegment.addPoint(new Vector(-15.010332325792092,102.58761800763077));
        firstSegment.addPoint(new Vector(-12.124000000000052,105.45999999999981));
        firstSegment.addPoint(new Vector(-9.059702094009708,108.04265065871749));
        firstSegment.addPoint(new Vector(-5.791160919785526,110.51584173248023));
        firstSegment.addPoint(new Vector(-2.4138572615254077,112.72533368363358));
        firstSegment.addPoint(new Vector(1.0781708981700717,114.70830791368184));
        firstSegment.addPoint(new Vector(4.6660199365480395,116.5027073357482));
        firstSegment.addPoint(new Vector(8.32524666598161,118.13978770011121));
        firstSegment.addPoint(new Vector(12.113702848616654,119.67641618499914));
        firstSegment.addPoint(new Vector(15.928233673142273,121.09804248021825));
        firstSegment.addPoint(new Vector(19.720935637040924,122.42061066111415));
        firstSegment.addPoint(new Vector(23.54531299845445,123.71597176924672));
        firstSegment.addPoint(new Vector(26.777000000000086,124.91099999999994));

        firstSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        firstSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path firstSegmentPath = firstSegment.generatePath();

        //First Back Segment (Backwards after Intake Two Balls)

        PathGenerator firstBackSegment = new PathGenerator(spacing, false);

        firstBackSegment.addPoint(new Vector(26.777000000000086,124.91099999999994));
        firstBackSegment.addPoint(new Vector(23.54531299845445,123.71597176924672));
        firstBackSegment.addPoint(new Vector(19.720935637040924,122.42061066111415));
        firstBackSegment.addPoint(new Vector(15.928233673142273,121.09804248021825));
        firstBackSegment.addPoint(new Vector(12.113702848616654,119.67641618499914));
        firstBackSegment.addPoint(new Vector(8.32524666598161,118.13978770011121));
        firstBackSegment.addPoint(new Vector(4.6660199365480395,116.5027073357482));
        firstBackSegment.addPoint(new Vector(1.0781708981700717,114.70830791368184));
        firstBackSegment.addPoint(new Vector(-2.4138572615254077,112.72533368363358));
        firstBackSegment.addPoint(new Vector(-5.791160919785526,110.51584173248023));
        firstBackSegment.addPoint(new Vector(-9.059702094009708,108.04265065871749));
        firstBackSegment.addPoint(new Vector(-12.124000000000052,105.45999999999981));
        firstBackSegment.addPoint(new Vector(-15.010332325792092,102.58761800763077));
        firstBackSegment.addPoint(new Vector(-16.828469529082895,99.01563692990226));
        firstBackSegment.addPoint(new Vector(-17.45600303702753,94.99983276452679));
        firstBackSegment.addPoint(new Vector(-17.333455599979047,90.9812027947782));
        firstBackSegment.addPoint(new Vector(-16.80323438639374,87.00796759082237));
        firstBackSegment.addPoint(new Vector(-16.03048652380278,83.06131703509911));
        firstBackSegment.addPoint(new Vector(-15.093200088128214,79.08766607570999));
        firstBackSegment.addPoint(new Vector(-14.072061068887905,75.18801634654949));
        firstBackSegment.addPoint(new Vector(-12.980033085037917,71.26350904779639));
        firstBackSegment.addPoint(new Vector(-11.858003756518372,67.36500946763454));
        firstBackSegment.addPoint(new Vector(-10.720902302507042,63.47119439151038));
        firstBackSegment.addPoint(new Vector(-9.595308353933135,59.61565853503009));
        firstBackSegment.addPoint(new Vector(-8.483850292598945,55.75950828845703));
        firstBackSegment.addPoint(new Vector(-7.386240243802803,51.85685480472273));
        firstBackSegment.addPoint(new Vector(-5.321459633874511,44.05829034560722));
        firstBackSegment.addPoint(new Vector(-4.368284946800898,40.129357068477844));
        firstBackSegment.addPoint(new Vector(-3.49316042768956   ,36.22561258542538));
        firstBackSegment.addPoint(new Vector(-2.6946502802075827,32.29401648786302));
        firstBackSegment.addPoint(new Vector(-1.9871308240016958,28.349751494710034));
        firstBackSegment.addPoint(new Vector(-1.3735342568415234,24.341291513736167));
        firstBackSegment.addPoint(new Vector(-0.8756010986213738,20.347441223216123));
        firstBackSegment.addPoint(new Vector(-0.4922460541405087,16.313403405380797));
        firstBackSegment.addPoint(new Vector(-0.22663426507743623,12.245540703187032));
        firstBackSegment.addPoint(new Vector(-0.07221798706818561,8.142432941288604));
        firstBackSegment.addPoint(new Vector(-0.00997438101811099,4.109449873323854));
        firstBackSegment.addPoint(new Vector(0,0));

        firstBackSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        firstBackSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path firstBackSegmentPath = firstBackSegment.generatePath();

        //Second Segment (Forward Spline)

        PathGenerator secondSegment = new PathGenerator(spacing, true);

        secondSegment.addPoint(new Vector(poseEstimator.getPose().x,poseEstimator.getPose().y));
        secondSegment.addPoint(new Vector(-0.05, 18.61));
        secondSegment.addPoint(new Vector(-1.52, 21.37));
        secondSegment.addPoint(new Vector(-2.49, 22.51));
        secondSegment.addPoint(new Vector(-5.31, 25.35));
        secondSegment.addPoint(new Vector(-7.24, 27.12));
        secondSegment.addPoint(new Vector(-9, 28.66));
        secondSegment.addPoint(new Vector(-10.4,29.82));
        secondSegment.addPoint(new Vector(-10.99, 30.40));
        secondSegment.addPoint(new Vector(-11.88, 31.15));
        secondSegment.addPoint(new Vector(-13.34, 32.38));
        secondSegment.addPoint(new Vector(-14.66, 33.47));
        secondSegment.addPoint(new Vector(-15.77, 34.39));
        secondSegment.addPoint(new Vector(-16.55, 35.03));
        secondSegment.addPoint(new Vector(-18.05, 36.25));
        secondSegment.addPoint(new Vector(-19.32, 37.28));
        secondSegment.addPoint(new Vector(-21.28, 38.86));
        secondSegment.addPoint(new Vector(-21.91, 39.37));
        secondSegment.addPoint(new Vector(-23.57, 40.7));
        secondSegment.addPoint(new Vector(-28.92, 44.98));
        secondSegment.addPoint(new Vector(-31.69, 47.22));
        secondSegment.addPoint(new Vector(-35,50));
        secondSegment.addPoint(new Vector(-37.62,52.27));
        secondSegment.addPoint(new Vector(-35,50));
        secondSegment.addPoint(new Vector(-37.31,52));
        secondSegment.addPoint(new Vector(-39.95,54.37));
        secondSegment.addPoint(new Vector(-41.10,55.42));
        secondSegment.addPoint(new Vector(-45.38, 59.51));
        secondSegment.addPoint(new Vector(-46.18,60.31));
        secondSegment.addPoint(new Vector(-46.91,61.04));
        secondSegment.addPoint(new Vector(-49.43,63.61));
        secondSegment.addPoint(new Vector(-51.66,65.98));
        secondSegment.addPoint(new Vector(-53.86,68.41));
        secondSegment.addPoint(new Vector(-55.51,70.31));
        secondSegment.addPoint(new Vector(-58.06,73.38));
        secondSegment.addPoint(new Vector(-59.79,75.61));
        secondSegment.addPoint(new Vector(-61,78.01));
        secondSegment.addPoint(new Vector(-64.09,82.01));
        secondSegment.addPoint(new Vector(-66.05,85.84));
        secondSegment.addPoint(new Vector(-67.46,90.03));
        secondSegment.addPoint(new Vector(-68, 96));
        secondSegment.addPoint(new Vector(-68, 100));
        secondSegment.addPoint(new Vector(-68, 110));
        secondSegment.addPoint(new Vector(-68, 120));
        secondSegment.addPoint(new Vector(-68, 130));
        secondSegment.addPoint(new Vector(-68, 140));
        secondSegment.addPoint(new Vector(-68, 150));
        secondSegment.addPoint(new Vector(-68, 160));
        secondSegment.addPoint(new Vector(-68, 170));
        secondSegment.addPoint(new Vector(-68, 174));

        secondSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        secondSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path secondSegmentPath = secondSegment.generatePath();

        //Third Segment (Backwards Spline)

        PathGenerator thirdSegment = new PathGenerator(spacing, false);

        thirdSegment.addPoint(new Vector(-68, 174));
        thirdSegment.addPoint(new Vector(-68, 170));
        thirdSegment.addPoint(new Vector(-68, 160));
        thirdSegment.addPoint(new Vector(-68, 150));
        thirdSegment.addPoint(new Vector(-68, 140));
        thirdSegment.addPoint(new Vector(-68, 130));
        thirdSegment.addPoint(new Vector(-68, 120));
        thirdSegment.addPoint(new Vector(-68, 110));
        thirdSegment.addPoint(new Vector(-68, 100));
        thirdSegment.addPoint(new Vector(-68, 96));
        thirdSegment.addPoint(new Vector(-67.46,90.03));
        thirdSegment.addPoint(new Vector(-66.05,85.84));
        thirdSegment.addPoint(new Vector(-64.09,82.01));
        thirdSegment.addPoint(new Vector(-61,78.01));
        thirdSegment.addPoint(new Vector(-59.79,75.61));
        thirdSegment.addPoint(new Vector(-58.06,73.38));
        thirdSegment.addPoint(new Vector(-55.51,70.31));
        thirdSegment.addPoint(new Vector(-53.86,68.41));
        thirdSegment.addPoint(new Vector(-51.66,65.98));
        thirdSegment.addPoint(new Vector(-49.43,63.61));
        thirdSegment.addPoint(new Vector(-46.91,61.04));
        thirdSegment.addPoint(new Vector(-46.18,60.31));
        thirdSegment.addPoint(new Vector(-45.38, 59.51));
        thirdSegment.addPoint(new Vector(-41.10,55.42));
        thirdSegment.addPoint(new Vector(-39.95,54.37));
        thirdSegment.addPoint(new Vector(-37.31,52));
        thirdSegment.addPoint(new Vector(-35,50));
        thirdSegment.addPoint(new Vector(-37.62,52.27));
        thirdSegment.addPoint(new Vector(-35,50));
        thirdSegment.addPoint(new Vector(-31.69, 47.22));
        thirdSegment.addPoint(new Vector(-28.92, 44.98));
        thirdSegment.addPoint(new Vector(-23.57, 40.7));
        thirdSegment.addPoint(new Vector(-21.91, 39.37));
        thirdSegment.addPoint(new Vector(-21.28, 38.86));
        thirdSegment.addPoint(new Vector(-19.32, 37.28));
        thirdSegment.addPoint(new Vector(-18.05, 36.25));
        thirdSegment.addPoint(new Vector(-16.55, 35.03));
        thirdSegment.addPoint(new Vector(-15.77, 34.39));
        thirdSegment.addPoint(new Vector(-14.66, 33.47));
        thirdSegment.addPoint(new Vector(-13.34, 32.38));
        thirdSegment.addPoint(new Vector(-11.88, 31.15));
        thirdSegment.addPoint(new Vector(-10.99, 30.40));
        thirdSegment.addPoint(new Vector(-10.4,29.82));
        thirdSegment.addPoint(new Vector(-9, 28.66));
        thirdSegment.addPoint(new Vector(-7.24, 27.12));
        thirdSegment.addPoint(new Vector(-5.31, 25.35));
        thirdSegment.addPoint(new Vector(-2.49, 22.51));
        thirdSegment.addPoint(new Vector(-1.52, 21.37));
        thirdSegment.addPoint(new Vector(-0.05, 18.61));
        thirdSegment.addPoint(new Vector(0, 18));

        thirdSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        thirdSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path thirdSegmentPath = thirdSegment.generatePath();

        rightTrenchCollectTenBallAutoPath = Arrays.asList(firstSegmentPath, firstBackSegmentPath, secondSegmentPath, thirdSegmentPath);
        return rightTrenchCollectTenBallAutoPath;
    }
}
