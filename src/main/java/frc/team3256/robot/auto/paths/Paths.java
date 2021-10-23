package frc.team3256.robot.auto.paths;

import frc.team3256.warriorlib.auto.purepursuit.Path;
import frc.team3256.warriorlib.auto.purepursuit.PathGenerator;
import frc.team3256.warriorlib.auto.purepursuit.PoseEstimator;
import frc.team3256.warriorlib.math.Vector;

import java.util.Arrays;
import java.util.List;

import static frc.team3256.robot.constants.DriveConstants.*;

public class Paths {

    // ROBOT DIMENSIONS: 31in x 42in
    private static List<Path> rightShootAutoPath;
    private static List<Path> rightBackShootAutoPath;
    private static List<Path> rightTrenchCollectAutoPath;
    private static List<Path> rightTrenchSixBallAutoPath;
    private static List<Path> rightTrenchCollectWallAutoPath;
    private static List<Path> rightTrenchCollectTenBallAutoPath;
    private static List<Path> stealTwoBallAutoPath;
    private static List<Path> barrelRacingPath;
    private static List<Path> bouncePath;
    private static List<Path> redGalacticSearchPathA;
    private static List<Path> redGalacticSearchPathB;
    private static List<Path> blueGalacticSearchPathB;
    private static List<Path> blueGalacticSearchPathA;
    private static PoseEstimator poseEstimator = PoseEstimator.getInstance();

    private static List<Path> slalomPath;

    public static void main(String args[]){
        Path p = getSlalomPath().get(0);
        for(Vector v : p.getRobotPath()){
            System.out.println(v.getVelocity());
        }
    }

    public static void initialize() {
        getRightShootAutoPath();
        getRightBackShootAutoPath();
        getRightTrenchCollectAutoPath();
        getRightTrenchSixBallAutoPath();
        getRightTrenchCollectWallAutoPath();
        getRightTrenchCollectTenBallAutoPath();
        getStealTwoBallAutoPath();
        getSlalomPath();
        getBarrelRacingPath();
        getBouncePath();
        getRedGalacticSearchPathA();
        getRedGalacticSearchPathB();
        getBlueGalacticSearchPathA();
        getBlueGalacticSearchPathB();
    }

    public static List<Path> getRedGalacticSearchPathA() {
        if (redGalacticSearchPathA != null)
            return redGalacticSearchPathA;

        PathGenerator s = new PathGenerator(spacing, true);

        s.addPoint(new Vector(0.0, 0.0));
        s.addPoint(new Vector(-0.2213576068699581, 6.007126760344708));
        s.addPoint(new Vector(-0.5585069939262723, 12.028263836803834));
        s.addPoint(new Vector(-0.9030294972796895, 18.062359525057214));
        s.addPoint(new Vector(-1.2252364891655958, 24.073426378099153));
        s.addPoint(new Vector(-1.4740928733947811, 30.110783851277997));
        s.addPoint(new Vector(-1.4518674757419205, 36.15554977352707));
        s.addPoint(new Vector(-1.7360189943882176, 42.17163345861792));
        s.addPoint(new Vector(-2.004126251010831, 48.19167982676933));
        s.addPoint(new Vector(-2.0467103634527035, 54.211699541658476));
        s.addPoint(new Vector(-1.504962765121519, 60.303212452189456));
        s.addPoint(new Vector(0.9962582980154053, 65.80623400298958));
        s.addPoint(new Vector(6.203360601619565, 68.8988239997089));
        s.addPoint(new Vector(12.0941718572642, 70.4214498186236));
        s.addPoint(new Vector(18.047634003480766, 71.63030876730966));
        s.addPoint(new Vector(23.923414724194842, 72.93151434083617));
        s.addPoint(new Vector(29.527797227703687, 75.08564830799787));
        s.addPoint(new Vector(33.32651800988805, 79.82125403325452));
        s.addPoint(new Vector(35.78728829671738, 85.39768942708793));
        s.addPoint(new Vector(37.43912864506471, 91.25010188523231));
        s.addPoint(new Vector(38.18426680054873, 97.30814307116117));
        s.addPoint(new Vector(37.7319136328875, 103.37139936248596));
        s.addPoint(new Vector(36.40870479850015, 109.28897564132973));
        s.addPoint(new Vector(34.586302675726955, 115.01025015778728));
        s.addPoint(new Vector(31.9514200556701, 120.46247429741902));
        s.addPoint(new Vector(27.21085382470055, 124.20007405230646));
        s.addPoint(new Vector(21.35979406338099, 125.74568983847584));
        s.addPoint(new Vector(15.448546994673322, 126.93297397929666));
        s.addPoint(new Vector(9.52367992604728, 128.03091463113438));
        s.addPoint(new Vector(3.5572468052564687, 129.1042052678015));
        s.addPoint(new Vector(-2.3744530339891554, 130.1662736132502));
        s.addPoint(new Vector(-8.3618949513493, 131.2518846725711));
        s.addPoint(new Vector(-14.320633376361997, 132.36313224647787));
        s.addPoint(new Vector(-20.27686846139339, 133.5257979065192));
        s.addPoint(new Vector(-26.191941003979565, 134.76347909970147));
        s.addPoint(new Vector(-32.066909656495454, 136.13298184814704));
        s.addPoint(new Vector(-37.93957600471195, 137.7834930760146));
        s.addPoint(new Vector(-43.51094562238667, 140.09026469859137));
        s.addPoint(new Vector(-48.623366208454556, 143.2432005352154));
        s.addPoint(new Vector(-53.74081847859394, 146.58912871280685));
        s.addPoint(new Vector(-54.66115950271971, 150.10907016078386));
        s.addPoint(new Vector(-55.71458666023453, 154.58439113605172));
        s.addPoint(new Vector(-55.45382726434231, 160.54790664948004));
        s.addPoint(new Vector(-55.13566576410916, 166.5592828244375));
        s.addPoint(new Vector(-55.76542628070563, 172.6197594741932));
        s.addPoint(new Vector(-55.3364680052078, 178.6968678890209));
        s.addPoint(new Vector(-55.84063120856537, 184.7792874644045));
        s.addPoint(new Vector(-55.26427338139892, 190.82962029112565));
        s.addPoint(new Vector(-55.59376004031687, 196.88225624754872));
//        s.addPoint(new Vector(-66.80458912289556, 202.98060493532086));
//        s.addPoint(new Vector(-66.85296618340878, 209.0199008889789));
        s.addPoint(new Vector(-55.67472620259501, 215.0302511282076));
        s.addPoint(new Vector(-55.14811544552613, 221.04189009924906));
        s.addPoint(new Vector(-55.07869381422789, 227.00249700036343));
        s.addPoint(new Vector(-55.485158302247456, 232.84691356263974));
        s.addPoint(new Vector(-54.10892621876819, 238.39717342921924));
        s.addPoint(new Vector(-53.868143222339924, 243.46298675134304));
        s.addPoint(new Vector(-52.14226406117372, 248.2455595795188));
        s.addPoint(new Vector(-50.185491666059065, 252.80644843345434));
        s.addPoint(new Vector(-46.06058576086416, 257.25922215518517));
        s.addPoint(new Vector(-41.84246000483101, 261.62232790704445));
        s.addPoint(new Vector(-37.582128924328245, 265.90677808226155));
        s.addPoint(new Vector(-33.2875741213785, 270.149010331868));
        s.addPoint(new Vector(-28.94381862871934, 274.3979346236072));
        s.addPoint(new Vector(-24.643764710784964, 278.59395022546727));
        s.addPoint(new Vector(-20.32149306224072, 282.83418018340024));
        s.addPoint(new Vector(-16.049535816351764, 287.087626693997));
        s.addPoint(new Vector(-11.805697303025312, 291.4356603159637));
        s.addPoint(new Vector(-7.6972633609218235, 295.8716975459545));
        s.addPoint(new Vector(-3.8136482312730067, 300.53019808598356));
        s.addPoint(new Vector(-0.5958693080854118, 305.6974936678391));
        s.addPoint(new Vector(0.0, 310.4039167686666));

        s.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        s.setVelocities(maxVel, maxAccel, maxVelk);

        Path sPath = s.generatePath();

        redGalacticSearchPathA = Arrays.asList(sPath);
        return redGalacticSearchPathA;
    }

    public static List<Path> getRedGalacticSearchPathB(){
        if (redGalacticSearchPathB != null)
            return redGalacticSearchPathB;

        PathGenerator s = new PathGenerator(spacing, true);

        s.addPoint(new Vector(0, 0));
        s.addPoint(new Vector(-0.1438430428280384, 6.041296219817799));
        s.addPoint(new Vector(-0.4147557942127946, 12.046504770129008));
        s.addPoint(new Vector(-0.9331178288561262, 18.055467069223244));
        s.addPoint(new Vector(-1.8171660992853447, 24.027698270365338));
        s.addPoint(new Vector(-3.1870202179586755, 29.911329754620088));
        s.addPoint(new Vector(-5.139523837788516, 35.587609767077936));
        s.addPoint(new Vector(-7.767205034110617, 40.999455615675444));
        s.addPoint(new Vector(-11.105150095361964, 46.04678524734305));
        s.addPoint(new Vector(-15.055816713420185, 50.590521578223104));
        s.addPoint(new Vector(-19.506279065883447, 54.64018261816892));
        s.addPoint(new Vector(-24.363303788909775, 58.302073197599384));
        s.addPoint(new Vector(-29.399186697076274, 61.65108571132676));
        s.addPoint(new Vector(-34.4664807013212, 64.90105697215395));
        s.addPoint(new Vector(-39.42156195136029, 68.38447665539752));
        s.addPoint(new Vector(-43.89923255266093, 72.50841762756588));
        s.addPoint(new Vector(-47.347968577225956, 77.44983069747857));
        s.addPoint(new Vector(-49.939503654315914, 82.92988851236677));
        s.addPoint(new Vector(-48.49995570040191, 88.77827438291392));
        s.addPoint(new Vector(-43.673995791928675, 92.48866949489378));
        s.addPoint(new Vector(-38.32560975156177, 95.22197497007852));
        s.addPoint(new Vector(-32.78439750824829, 97.5725905923611));
        s.addPoint(new Vector(-27.120223990922128, 99.74188735146875));
        s.addPoint(new Vector(-21.42485205909432, 101.80380991656473));
        s.addPoint(new Vector(-15.737180359335142, 103.8100973380888));
        s.addPoint(new Vector(-10.019820074489346, 105.81946138186821));
        s.addPoint(new Vector(-4.288997938544426, 107.86371838616871));
        s.addPoint(new Vector(1.3576894524117478, 109.94264947095378));
        s.addPoint(new Vector(7.017522974752183, 112.12944989330309));
        s.addPoint(new Vector(12.589674008256864, 114.43007909509164));
        s.addPoint(new Vector(18.134568327996234, 116.92961428210081));
        s.addPoint(new Vector(23.49747648766912, 119.64168705060744));
        s.addPoint(new Vector(28.701366070315814, 122.70803160001991));
        s.addPoint(new Vector(33.55462422161928, 126.2449171896282));
        s.addPoint(new Vector(37.81418129946131, 130.5212143362131));
        s.addPoint(new Vector(40.78799284168001, 135.744178417101));
        s.addPoint(new Vector(41.56824542363721, 141.778367314732));
        s.addPoint(new Vector(40.16632867908599, 147.70031987458668));
        s.addPoint(new Vector(37.6174102512136, 153.2118469018913));
        s.addPoint(new Vector(34.738246363824985, 158.53536314088868));
        s.addPoint(new Vector(31.594787074458296, 163.76521237048038));
        s.addPoint(new Vector(27.91897665614657, 168.60643829225197));
        s.addPoint(new Vector(23.50857482491743, 172.83267155208085));
        s.addPoint(new Vector(18.454321926045566, 176.18477357711876));
        s.addPoint(new Vector(12.924242155998584, 178.64549084168567));
        s.addPoint(new Vector(7.126027410341777, 180.32563195961416));
        s.addPoint(new Vector(1.170814940782364, 181.40141271343913));
        s.addPoint(new Vector(-4.848941343423846, 182.0309694095222));
        s.addPoint(new Vector(-10.859444502804337, 182.35234373598996));
        s.addPoint(new Vector(-16.867938940098128, 182.48792108722694));
        s.addPoint(new Vector(-22.878257326828802, 182.54884520294803));
        s.addPoint(new Vector(-28.990173896799888, 182.65906709424843));
        s.addPoint(new Vector(-34.98202232521871, 182.97683176856748));
        s.addPoint(new Vector(-40.97761009545577, 183.77056347257633));
        s.addPoint(new Vector(-46.76323970016479, 185.5345752220624));
        s.addPoint(new Vector(-51.65336684717114, 189.0321162838977));
        s.addPoint(new Vector(-54.658696467660505, 194.2754878090131));
        s.addPoint(new Vector(-56.3515470349282, 200.0582415975141));
        s.addPoint(new Vector(-57.7541951275512, 205.93142483318135));
        s.addPoint(new Vector(-58.776413552933164, 211.93092211759847));
        s.addPoint(new Vector(-59.44107976093743, 217.90483375813503));
        s.addPoint(new Vector(-59.85653199056482, 223.95326251313085));
        s.addPoint(new Vector(-60.08578847347793, 230.0024062674491));
        s.addPoint(new Vector(-60.17677606377744, 236.04870559019332));
        s.addPoint(new Vector(-60.161894917209125, 242.08017216620482));
        s.addPoint(new Vector(-60.063608443397285, 248.12065636016905));
        s.addPoint(new Vector(-59.89896136630712, 254.1399734009887));
        s.addPoint(new Vector(-59.679242501401276, 260.1851919204064));
        s.addPoint(new Vector(-59.41590902677238, 266.2211794882879));
        s.addPoint(new Vector(-59.11786044811525, 272.2600150070692));
        s.addPoint(new Vector(-58.79659494366253, 278.26610328417087));
        s.addPoint(new Vector(-58.46096788781061, 284.29543598053385));
        s.addPoint(new Vector(-58.132364050081904, 290.302124459532));
        s.addPoint(new Vector(-57.850591678526555, 296.337672172667));
        s.addPoint(new Vector(-57.75431498242061, 302.3880794566055));
        s.addPoint(new Vector(-57.76912612365332, 303.361234611954));



        s.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        s.setVelocities(maxVel, maxAccel, 10);

        Path sPath = s.generatePath();

        redGalacticSearchPathB = Arrays.asList(sPath);
        return redGalacticSearchPathB;
    }

    public static List<Path> getBlueGalacticSearchPathA(){
        if (blueGalacticSearchPathA != null)
            return blueGalacticSearchPathA;

        PathGenerator s = new PathGenerator(spacing, true);

        s.addPoint(new Vector(0, 0));
        s.addPoint(new Vector(0.113636686421, 6.03161654339));
        s.addPoint(new Vector(0.779364089308, 12.0373419917));
        s.addPoint(new Vector(2.19013554234, 17.9338262063));
        s.addPoint(new Vector(4.25820506529, 23.6189852723));
        s.addPoint(new Vector(6.81690869303, 29.0700733379));
        s.addPoint(new Vector(9.74541509237, 34.3453258187));
        s.addPoint(new Vector(12.9281574097, 39.4471537972));
        s.addPoint(new Vector(16.347462593, 44.4836774559));
        s.addPoint(new Vector(19.9074194596, 49.408385772));
        s.addPoint(new Vector(23.5910368383, 54.2712186171));
        s.addPoint(new Vector(27.3300319304, 59.0390766285));
        s.addPoint(new Vector(31.1398583798, 63.7773391327));
        s.addPoint(new Vector(34.9708053336, 68.4617539496));
        s.addPoint(new Vector(38.8558072809, 73.1656958015));
        s.addPoint(new Vector(42.7514369978, 77.8676551885));
        s.addPoint(new Vector(46.6134536952, 82.5457545174));
        s.addPoint(new Vector(50.4735143202, 87.2719561198));
        s.addPoint(new Vector(54.278542207, 92.0196364548));
        s.addPoint(new Vector(57.970828789, 96.7599199165));
        s.addPoint(new Vector(61.6160641467, 101.636634729));
        s.addPoint(new Vector(65.1114077391, 106.597762828));
        s.addPoint(new Vector(68.3872921277, 111.663303481));
        s.addPoint(new Vector(71.3615148233, 116.901790881));
        s.addPoint(new Vector(73.8687750435, 122.394638878));
        s.addPoint(new Vector(75.5722534021, 128.246583605));
        s.addPoint(new Vector(75.7850151395, 134.276822218));
        s.addPoint(new Vector(73.8034350643, 139.968729448));
        s.addPoint(new Vector(70.0016801754, 144.635973075));
        s.addPoint(new Vector(65.4336922973, 148.590100561));
        s.addPoint(new Vector(60.6808040776, 152.384898632));
        s.addPoint(new Vector(55.6777650353, 155.892542666));
        s.addPoint(new Vector(50.4881403839, 158.953481574));
        s.addPoint(new Vector(45.0899396357, 161.638543857));
        s.addPoint(new Vector(39.5648426142, 163.996247782));
        s.addPoint(new Vector(33.9202936773, 166.104473793));
        s.addPoint(new Vector(28.2058241829, 168.008121796));
        s.addPoint(new Vector(22.4490717136, 169.748582694));
        s.addPoint(new Vector(16.6572449592, 171.363206172));
        s.addPoint(new Vector(10.8224020467, 172.885402405));
        s.addPoint(new Vector(4.92867119673, 174.345072755));
        s.addPoint(new Vector(-0.950729824227, 175.748359943));
        s.addPoint(new Vector(-6.82716120605, 177.122265012));
        s.addPoint(new Vector(-12.6991652797, 178.49192166));
        s.addPoint(new Vector(-18.5418208994, 179.881550326));
        s.addPoint(new Vector(-24.3677352637, 181.334247364));
        s.addPoint(new Vector(-30.174658203, 182.912093031));
        s.addPoint(new Vector(-35.9676635026, 184.735763858));
        s.addPoint(new Vector(-41.5693604892, 187.030367819));
        s.addPoint(new Vector(-46.4094969622, 190.597546607));
        s.addPoint(new Vector(-47.0820269044, 196.567264687));
        s.addPoint(new Vector(-45.0462911026, 202.307226908));
        s.addPoint(new Vector(-41.5545641112, 207.253486673));
        s.addPoint(new Vector(-37.4452246476, 211.669966057));
        s.addPoint(new Vector(-33.067008607, 215.795280511));
        s.addPoint(new Vector(-28.5400529734, 219.754881228));
        s.addPoint(new Vector(-23.8843536755, 223.646310659));
        s.addPoint(new Vector(-19.1939317918, 227.460919755));
        s.addPoint(new Vector(-14.470235031, 231.248636466));
        s.addPoint(new Vector(-9.69776893878, 235.06463474));
        s.addPoint(new Vector(-4.97027914151, 238.878195033));
        s.addPoint(new Vector(-0.316183044818, 242.719534171));
        s.addPoint(new Vector(4.28250315631, 246.683021743));
        s.addPoint(new Vector(8.6835726278, 250.781367424));
        s.addPoint(new Vector(12.8427151788, 255.243128923));
        s.addPoint(new Vector(16.4611176129, 260.034058459));
        s.addPoint(new Vector(19.8728761874, 264.987751099));
        s.addPoint(new Vector(23.1354225258, 270.089608779));
        s.addPoint(new Vector(26.2506582656, 275.267634805));
        s.addPoint(new Vector(29.2906377743, 280.545408136));
        s.addPoint(new Vector(32.2409291851, 285.843268787));
        s.addPoint(new Vector(35.1351801331, 291.193272674));
        s.addPoint(new Vector(37.9746886142, 296.589816667));
        s.addPoint(new Vector(40.7050499592, 301.934110895));
        s.addPoint(new Vector(43.3717014512, 307.343387364));
        s.addPoint(new Vector(45.9338047518, 312.818710266));
        s.addPoint(new Vector(48.2693691269, 318.354726574));
        s.addPoint(new Vector(49.6448806101, 324.221490186));
        s.addPoint(new Vector(49.6448806101, 324.221490186));

        s.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        s.setVelocities(maxVel, maxAccel, maxVelk);

        Path sPath = s.generatePath();

        blueGalacticSearchPathA = Arrays.asList(sPath);
        return blueGalacticSearchPathA;
    }

    public static List<Path> getBlueGalacticSearchPathB(){
        if (blueGalacticSearchPathB != null)
            return blueGalacticSearchPathB;

        PathGenerator s = new PathGenerator(spacing, true);

        s.addPoint(new Vector(0.0, 0.0));
        s.addPoint(new Vector(0.04442148945317115, 6.046900938669147));
        s.addPoint(new Vector(0.3008449678104057, 12.076812869432324));
        s.addPoint(new Vector(0.842715175819805, 18.085918512061163));
        s.addPoint(new Vector(1.6488090111552793, 24.037702027946274));
        s.addPoint(new Vector(2.682794670493138, 29.989368916434138));
        s.addPoint(new Vector(3.8994501186888613, 35.9260828378711));
        s.addPoint(new Vector(5.260679635124376, 41.84463326917909));
        s.addPoint(new Vector(6.739747326584052, 47.759783145443));
        s.addPoint(new Vector(8.294140809654493, 53.60376003752177));
        s.addPoint(new Vector(9.913262364536067, 59.416807286142465));
        s.addPoint(new Vector(11.596398427241425, 65.25394946300958));
        s.addPoint(new Vector(13.317804003453759, 71.07334442410377));
        s.addPoint(new Vector(15.052585496491844, 76.83476387577554));
        s.addPoint(new Vector(16.809854210685415, 82.60723096144338));
        s.addPoint(new Vector(18.56733853607723, 88.35373331587786));
        s.addPoint(new Vector(20.33429934956594, 94.14141033658942));
        s.addPoint(new Vector(22.0851685409946, 99.92661336504449));
        s.addPoint(new Vector(23.821441411344054, 105.76145164631316));
        s.addPoint(new Vector(25.50777917006795, 111.58493438525147));
        s.addPoint(new Vector(27.129413568053764, 117.42011918978451));
        s.addPoint(new Vector(28.655991857687567, 123.26249973042442));
        s.addPoint(new Vector(30.054539160161937, 129.1502522517122));
        s.addPoint(new Vector(31.2504325163234, 135.04902637300398));
        s.addPoint(new Vector(32.14050927728269, 141.0168843785127));
        s.addPoint(new Vector(32.500529479087675, 147.0913445610774));
        s.addPoint(new Vector(31.894253177484757, 153.0885212539307));
        s.addPoint(new Vector(30.09439922519536, 158.91403363758994));
        s.addPoint(new Vector(27.53535160371625, 164.37846376161386));
        s.addPoint(new Vector(23.61154671565322, 169.00049038216912));
        s.addPoint(new Vector(18.765880368539257, 172.67243033011744));
        s.addPoint(new Vector(13.537197983637071, 175.67173220326694));
        s.addPoint(new Vector(8.038612874303169, 178.32929500168302));
        s.addPoint(new Vector(2.526211057212336, 180.7505347980043));
        s.addPoint(new Vector(-3.05117514076008, 183.11664967545687));
        s.addPoint(new Vector(-5.582770314820394, 185.511552816475));
        s.addPoint(new Vector(-8.04968391760221, 188.06953535740305));
        s.addPoint(new Vector(-9.35283080782264, 190.9418718054492));
        s.addPoint(new Vector(-10.30783548084254, 194.36397250717368));
        s.addPoint(new Vector(-13.47237232069024, 198.68756167175778));
        s.addPoint(new Vector(-17.966126560426872, 204.2107414539584));
        s.addPoint(new Vector(-17.283784182587112, 210.21442472978873));
        s.addPoint(new Vector(-17.60823364565094, 216.1874577185803));
        s.addPoint(new Vector(-14.026388376044224, 221.99701070414912));
        s.addPoint(new Vector(-13.770016992286997, 227.12691008177012));
        s.addPoint(new Vector(-12.31765257660561, 231.25655193833416));
        s.addPoint(new Vector(-11.27128466047587, 234.649658813903));
        s.addPoint(new Vector(-8.963399204813896, 237.5852822687939));
        s.addPoint(new Vector(-5.487456356092821, 240.29614884274037));
        s.addPoint(new Vector(-0.009377166064382436, 242.88796795833287));
        s.addPoint(new Vector(5.462393757160925, 245.50890418023107));
        s.addPoint(new Vector(10.81790631420995, 248.257464787892));
        s.addPoint(new Vector(16.006315018519587, 251.29679356172346));
        s.addPoint(new Vector(16.93419995924998, 257.87900107421478));
        s.addPoint(new Vector(22.1505540118454, 259.18421960618946));
        s.addPoint(new Vector(22.11626874814442, 264.4103405503463));
//        s.addPoint(new Vector(21.407233119697963, 270.33742224479283));
//        s.addPoint(new Vector(21.505472374812868, 276.3827426418454));
//        s.addPoint(new Vector(21.39380175840803, 282.4486206762691));
//        s.addPoint(new Vector(21.29245581214353, 288.5099166199967));
//        s.addPoint(new Vector(21.208710669890777, 294.5457916631652));
        s.addPoint(new Vector(22.146906865763, 300.61028712897996));
        s.addPoint(new Vector(22.11076726426967, 306.71445355413556));
        s.addPoint(new Vector(22.100614323516538, 312.72610044741197));
        s.addPoint(new Vector(22.094856629252163, 318.7457132549643));
//        s.addPoint(new Vector(29.008471045687898, 322.2201050873555));

        s.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        s.setVelocities(maxVel, maxAccel, maxVelk);

        Path sPath = s.generatePath();

        blueGalacticSearchPathB = Arrays.asList(sPath);
        return blueGalacticSearchPathB;
    }

    public static List<Path> getSlalomPath() {
        if (slalomPath != null)
            return slalomPath;

        // STARTING POINT (40, 30)

        PathGenerator s = new PathGenerator(spacing, true);

        s.addPoint(new Vector(0, 0));
        s.addPoint(new Vector(-0.0524297928555, 6.06537210977));
        s.addPoint(new Vector(-0.399487161145, 12.0842276336));
        s.addPoint(new Vector(-1.30054094424, 18.0165924166));
        s.addPoint(new Vector(-3.00262483464, 23.8113900905));
        s.addPoint(new Vector(-5.64355144331, 29.2247378497));
        s.addPoint(new Vector(-9.21901328265, 34.0782685714));
        s.addPoint(new Vector(-13.5794632544, 38.2723179868));
        s.addPoint(new Vector(-18.4606443689, 41.7838707597));
        s.addPoint(new Vector(-23.7155524089, 44.7422858686));
        s.addPoint(new Vector(-29.1994955885, 47.2598927134));
        s.addPoint(new Vector(-34.8367749224, 49.4698354439));
        s.addPoint(new Vector(-40.5614272179, 51.5125026208));
        s.addPoint(new Vector(-46.2686229197, 53.5722247743));
        s.addPoint(new Vector(-51.8494686817, 56.030159997));
        s.addPoint(new Vector(-56.8661880045, 59.3290620633));
        s.addPoint(new Vector(-61.3332233798, 63.4032136243));
        s.addPoint(new Vector(-64.7692438742, 68.368430943));
        s.addPoint(new Vector(-67.4755707095, 73.7843380902));
        s.addPoint(new Vector(-69.7029975058, 79.3801146963));
        s.addPoint(new Vector(-71.6223253562, 85.1539837051));
        s.addPoint(new Vector(-73.2799108787, 90.998771918));
        s.addPoint(new Vector(-74.7195542486, 96.907821815));
        s.addPoint(new Vector(-75.9533752114, 102.831006472));
        s.addPoint(new Vector(-76.9897500794, 108.745793027));
        s.addPoint(new Vector(-77.8446263837, 114.744545571));
        s.addPoint(new Vector(-78.4973851816, 120.718226813));
        s.addPoint(new Vector(-78.9552412576, 126.765213413));
        s.addPoint(new Vector(-79.2251209322, 132.830741871));
        s.addPoint(new Vector(-79.3686332712, 138.900690141));
        s.addPoint(new Vector(-79.476262887, 144.939227183));
        s.addPoint(new Vector(-79.5294658434, 150.966476462));
        s.addPoint(new Vector(-79.5038990395, 157.060211426));
        s.addPoint(new Vector(-79.3922260194, 163.124028854));
        s.addPoint(new Vector(-79.1902225423, 169.233811657));
        s.addPoint(new Vector(-78.9000904798, 175.251592415));
        s.addPoint(new Vector(-78.5113463521, 181.292518976));
        s.addPoint(new Vector(-78.0107703801, 187.365927189));
        s.addPoint(new Vector(-77.3918536447, 193.352887777));
        s.addPoint(new Vector(-76.6172348202, 199.360818271));
        s.addPoint(new Vector(-75.649446237, 205.327230392));
        s.addPoint(new Vector(-74.4193450947, 211.216341681));
        s.addPoint(new Vector(-72.7860079662, 217.000023576));
        s.addPoint(new Vector(-70.4491013452, 222.530172046));
        s.addPoint(new Vector(-66.7134887812, 227.272337926));
        s.addPoint(new Vector(-61.6002691941, 230.558679661));
        s.addPoint(new Vector(-56.0625460557, 232.955127692));
        s.addPoint(new Vector(-50.2658288984, 234.786453047));
        s.addPoint(new Vector(-44.4155760547, 236.38673726));
        s.addPoint(new Vector(-38.594830391, 237.903564744));
        s.addPoint(new Vector(-32.6891143472, 239.45837021));
        s.addPoint(new Vector(-26.8461951193, 241.082007359));
        s.addPoint(new Vector(-21.0698369241, 242.841997923));
        s.addPoint(new Vector(-15.3728560964, 244.822509369));
        s.addPoint(new Vector(-9.75352288026, 247.167428296));
        s.addPoint(new Vector(-4.46956229094, 250.014137958));
        s.addPoint(new Vector(0.277809171538, 253.754227323));
        s.addPoint(new Vector(3.70055382273, 258.694385288));
        s.addPoint(new Vector(5.55373131286, 264.454409504));
        s.addPoint(new Vector(6.58047491691, 270.377904704));
        s.addPoint(new Vector(5.64507520383, 276.376499012));
        s.addPoint(new Vector(2.55684289416, 281.609260917));
        s.addPoint(new Vector(-1.78460299225, 285.761360121));
        s.addPoint(new Vector(-6.83253817994, 289.037849116));
        s.addPoint(new Vector(-12.3439702432, 291.564570898));
        s.addPoint(new Vector(-18.1151182168, 293.338925297));
        s.addPoint(new Vector(-24.0544272003, 294.317569698));
        s.addPoint(new Vector(-30.1233230359, 294.401760253));
        s.addPoint(new Vector(-36.1161195041, 293.634538398));
        s.addPoint(new Vector(-42.0576978712, 292.431500256));
        s.addPoint(new Vector(-47.7581019551, 290.392224467));
        s.addPoint(new Vector(-53.0163598284, 287.474834772));
        s.addPoint(new Vector(-57.8746102653, 283.841586925));
        s.addPoint(new Vector(-62.2278726041, 279.690410457));
        s.addPoint(new Vector(-66.0641864721, 275.018402929));
        s.addPoint(new Vector(-69.1641514843, 269.836460907));
        s.addPoint(new Vector(-71.0612660371, 264.065349177));
        s.addPoint(new Vector(-70.6297504133, 257.995282283));
        s.addPoint(new Vector(-67.1909370873, 253.000660825));
        s.addPoint(new Vector(-62.3608589648, 249.428719163));
        s.addPoint(new Vector(-57.2177591704, 246.182130877));
        s.addPoint(new Vector(-51.8246095614, 243.342140242));
        s.addPoint(new Vector(-46.2496367507, 241.065858986));
        s.addPoint(new Vector(-40.4812298008, 239.2189141));
        s.addPoint(new Vector(-34.5952765434, 237.615084574));
        s.addPoint(new Vector(-28.7656365221, 236.020508161));
        s.addPoint(new Vector(-23.0332473285, 234.016786321));
        s.addPoint(new Vector(-17.8419000302, 230.953329186));
        s.addPoint(new Vector(-13.9327514251, 226.378949553));
        s.addPoint(new Vector(-9.97147472493, 221.796525774));
        s.addPoint(new Vector(-4.18262119865, 219.899415006));
        s.addPoint(new Vector(-0.396706005129, 215.228171165));
        s.addPoint(new Vector(1.27742247393, 209.454029756));
        s.addPoint(new Vector(2.27001715696, 203.42174431));
        s.addPoint(new Vector(2.91681788144, 197.400608383));
        s.addPoint(new Vector(3.36295107007, 191.417133659));
        s.addPoint(new Vector(3.68327423392, 185.370707361));
        s.addPoint(new Vector(3.91195537775, 179.306635833));
        s.addPoint(new Vector(4.07175211043, 173.294447026));
        s.addPoint(new Vector(4.18225929512, 167.25026242));
        s.addPoint(new Vector(4.25566106476, 161.151731996));
        s.addPoint(new Vector(4.30096213404, 155.073431172));
        s.addPoint(new Vector(4.32722658461, 148.965595234));
        s.addPoint(new Vector(4.34216417904, 142.890756339));
        s.addPoint(new Vector(4.35173354764, 136.790031313));
        s.addPoint(new Vector(4.3307535586, 130.722779895));
        s.addPoint(new Vector(4.23194983229, 124.622043343));
        s.addPoint(new Vector(4.00940885983, 118.541940309));
        s.addPoint(new Vector(3.62019871891, 112.535012098));
        s.addPoint(new Vector(3.0121169141, 106.544983556));
        s.addPoint(new Vector(2.11954287079, 100.534696855));
        s.addPoint(new Vector(0.882655953871, 94.5867259099));
        s.addPoint(new Vector(-0.754889138885, 88.7852786762));
        s.addPoint(new Vector(-2.87610064626, 83.133594485));
        s.addPoint(new Vector(-5.53544126251, 77.7332711092));
        s.addPoint(new Vector(-8.76987446934, 72.6775344066));
        s.addPoint(new Vector(-12.5917644278, 68.0445096325));
        s.addPoint(new Vector(-16.9818854812, 63.8901648884));
        s.addPoint(new Vector(-21.8262273293, 60.2791687805));
        s.addPoint(new Vector(-27.0214449394, 57.1832436643));
        s.addPoint(new Vector(-32.4301901043, 54.5417615911));
        s.addPoint(new Vector(-37.9955037882, 52.2182405268));
        s.addPoint(new Vector(-43.6535719719, 50.0465990366));
        s.addPoint(new Vector(-49.2705819619, 47.8058204577));
        s.addPoint(new Vector(-54.6571646391, 45.1040190697));
        s.addPoint(new Vector(-59.3190461872, 41.2520018574));
        s.addPoint(new Vector(-62.2496444367, 35.9168870917));
        s.addPoint(new Vector(-63.924795295, 30.1106290253));
        s.addPoint(new Vector(-65.3896193415, 24.2196434844));
        s.addPoint(new Vector(-66.5790207769, 18.32761829));
        s.addPoint(new Vector(-67.5418142616, 12.3977211026));
        s.addPoint(new Vector(-68.3329259454, 6.42901801674));
        s.addPoint(new Vector(-68.9888247138, 0.461058890445));
        s.addPoint(new Vector(-69.5467015327, -5.60033446216));
        s.addPoint(new Vector(-70.0152062234, -11.654353412));
        s.addPoint(new Vector(-70.4054528257, -17.656011406));
        s.addPoint(new Vector(-70.7320931891, -23.6993998441));
        s.addPoint(new Vector(-70.9953972938, -29.7313030238));
        s.addPoint(new Vector(-71.1920597863, -35.735551835));
        s.addPoint(new Vector(-71.2626578322, -38.6723340418));

        s.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        s.setVelocities(maxVel, maxAccel, 5);

        Path sPath = s.generatePath();

        slalomPath = Arrays.asList(sPath);
        return slalomPath;
    }

    public static List<Path> getBarrelRacingPath() {

        // STARTING POSITION: (40, 90)

        if (barrelRacingPath != null) return barrelRacingPath;

        PathGenerator s = new PathGenerator(spacing, true);
        s.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        s.setVelocities(maxVel, maxAccel, 6);

        s.addPoint(new Vector(0.0, 0.0));
        s.addPoint(new Vector(0.010905035212999792, 6.02055115743574));
        s.addPoint(new Vector(0.061270628778814284, 12.104266996505459));
        s.addPoint(new Vector(0.14911212491779224, 18.148641633206942));
        s.addPoint(new Vector(0.2692563949232891, 24.183341289532535));
        s.addPoint(new Vector(0.41998005137902794, 30.246781226593384));
        s.addPoint(new Vector(0.5995302772148108, 36.29982606951438));
        s.addPoint(new Vector(0.8073289297323356, 42.325145968600125));
        s.addPoint(new Vector(1.046201823541054, 48.37458131560521));
        s.addPoint(new Vector(1.318352418220897, 54.44801215373826));
        s.addPoint(new Vector(1.6224699572196641, 60.46387336540917));
        s.addPoint(new Vector(1.969120965631916, 66.5569487745154));
        s.addPoint(new Vector(2.353346345395181, 72.56046195359006));
        s.addPoint(new Vector(2.7901501180162143, 78.61791981434988));
        s.addPoint(new Vector(3.283397525365231, 84.66109738646867));
        s.addPoint(new Vector(3.8462883891699278, 90.71254409500824));
        s.addPoint(new Vector(4.484949816358551, 96.68114445536438));
        s.addPoint(new Vector(5.228069810688268, 102.63618355475109));
        s.addPoint(new Vector(6.111939942284394, 108.57978797078849));
        s.addPoint(new Vector(7.194612346538051, 114.48761714690895));
        s.addPoint(new Vector(8.59160258904572, 120.332420955146));
        s.addPoint(new Vector(10.59180349299723, 126.05786323902561));
        s.addPoint(new Vector(13.991933427859294, 131.10225056693537));
        s.addPoint(new Vector(19.03372748510101, 134.43632780974355));
        s.addPoint(new Vector(24.775541273262547, 136.33751091843425));
        s.addPoint(new Vector(30.846938027316256, 136.0651166263007));
        s.addPoint(new Vector(36.64386686179074, 134.2919265309495));
        s.addPoint(new Vector(42.097080518173414, 131.62787979506075));
        s.addPoint(new Vector(47.225518680516586, 128.33075046558574));
        s.addPoint(new Vector(51.865879090029125, 124.49835795024481));
        s.addPoint(new Vector(55.437769435723425, 119.63755600527173));
        s.addPoint(new Vector(56.06369526448222, 113.65325657391327));
        s.addPoint(new Vector(55.45876717264295, 107.67470390297674));
        s.addPoint(new Vector(54.23036955898044, 101.73930563479985));
        s.addPoint(new Vector(52.42325248091652, 95.98870775409483));
        s.addPoint(new Vector(49.895888376688134, 90.49292045842225));
        s.addPoint(new Vector(46.377973140097424, 85.58313183152659));
        s.addPoint(new Vector(41.50253524927524, 82.03019108036753));
        s.addPoint(new Vector(35.564473641371535, 80.98691214265786));
        s.addPoint(new Vector(29.651218350735746, 82.2287068360325));
        s.addPoint(new Vector(24.08139842289367, 84.53252454214041));
        s.addPoint(new Vector(18.65030566453548, 87.25843839118816));
        s.addPoint(new Vector(13.97740324124095, 91.03471156130448));
        s.addPoint(new Vector(10.41443511598294, 95.90085895206991));
        s.addPoint(new Vector(7.798606655717535, 101.33723121886925));
        s.addPoint(new Vector(5.820332404908015, 107.0744012949923));
        s.addPoint(new Vector(4.2994406713192745, 112.88567052678053));
        s.addPoint(new Vector(3.08059818729555, 118.79281402228281));
        s.addPoint(new Vector(2.081742169239533, 124.78400910019991));
        s.addPoint(new Vector(1.2536274475269806, 130.81810055674947));
        s.addPoint(new Vector(0.5676284239723515, 136.7868289827601));
        s.addPoint(new Vector(-0.020762180796211283, 142.803842563766));
        s.addPoint(new Vector(-0.5276345744379825, 148.7993133800719));
        s.addPoint(new Vector(-0.9757247195845338, 154.81199259339));
        s.addPoint(new Vector(-1.3840984665001201, 160.88166503180236));
        s.addPoint(new Vector(-1.7639439205085239, 166.9398075031425));
        s.addPoint(new Vector(-2.133532407038416, 173.00982274439946));
        s.addPoint(new Vector(-2.5061743586085186, 179.00238817140897));
        s.addPoint(new Vector(-2.90932058558181, 184.99124820085675));
        s.addPoint(new Vector(-3.375857736150678, 190.98694460691416));
        s.addPoint(new Vector(-3.9562528851892864, 196.9822583413492));
        s.addPoint(new Vector(-4.737517508617728, 202.93490254099416));
        s.addPoint(new Vector(-5.926157817369656, 208.87597869789755));
        s.addPoint(new Vector(-7.990268861880594, 214.6063421825959));
        s.addPoint(new Vector(-11.056181424545358, 219.88292620327417));
        s.addPoint(new Vector(-15.133929279266525, 224.30665875984943));
        s.addPoint(new Vector(-20.4282436560286, 227.1684466223603));
        s.addPoint(new Vector(-26.28385532657076, 228.5560341447994));
        s.addPoint(new Vector(-32.28955142513006, 228.78718827644644));
        s.addPoint(new Vector(-38.25523684154669, 228.02073424369553));
        s.addPoint(new Vector(-44.04020262180239, 226.22051380971646));
        s.addPoint(new Vector(-49.26879199157281, 223.20573148414888));
        s.addPoint(new Vector(-53.28812891289158, 218.6536834601182));
        s.addPoint(new Vector(-55.0732301097934, 212.91980966435764));
        s.addPoint(new Vector(-54.94930510485443, 206.87933389078063));
        s.addPoint(new Vector(-54.258988266169354, 200.85326503139225));
        s.addPoint(new Vector(-53.0980948765104, 194.84931402202497));
        s.addPoint(new Vector(-51.081781778406395, 189.12977846815076));
        s.addPoint(new Vector(-47.95763457953433, 183.95032811661986));
        s.addPoint(new Vector(-43.58862194234425, 179.69967643806828));
        s.addPoint(new Vector(-38.16179159337196, 176.95912372135768));
        s.addPoint(new Vector(-32.17413589171255, 176.09099007506376));
        s.addPoint(new Vector(-26.19010032484364, 176.95233667927386));
        s.addPoint(new Vector(-20.438322904935262, 178.99008544160372));
        s.addPoint(new Vector(-14.915492429900311, 181.46156989775562));
        s.addPoint(new Vector(-9.594065303720896, 184.25995489176447));
        s.addPoint(new Vector(-4.481265627667966, 187.51302053780321));
        s.addPoint(new Vector(0.41375345279539033, 191.05452070750465));
        s.addPoint(new Vector(5.182082083269393, 194.82230803025638));
        s.addPoint(new Vector(9.796051162089569, 198.7182808114594));
        s.addPoint(new Vector(14.29850389201701, 202.7320063796376));
        s.addPoint(new Vector(18.682690388614475, 206.83131549103004));
        s.addPoint(new Vector(23.009884335727136, 211.06300985751193));
        s.addPoint(new Vector(27.206117932922353, 215.3534153133968));
        s.addPoint(new Vector(31.335755498203483, 219.77662789172757));
        s.addPoint(new Vector(35.357100319171536, 224.310417565703));
        s.addPoint(new Vector(39.25219983046193, 228.96987992367906));
        s.addPoint(new Vector(42.93995154071615, 233.707840434741));
        s.addPoint(new Vector(46.46753687299099, 238.67006206234095));
        s.addPoint(new Vector(49.703198649241074, 243.79261288837756));
        s.addPoint(new Vector(52.6228846565441, 249.12039919654893));
        s.addPoint(new Vector(55.30170637497011, 254.55881307344822));
        s.addPoint(new Vector(57.36103638470678, 260.21803917023686));
        s.addPoint(new Vector(57.55170668653605, 266.25638504604836));
        s.addPoint(new Vector(55.44690534741392, 271.9466030258021));
        s.addPoint(new Vector(51.797834548718726, 276.72796505160613));
        s.addPoint(new Vector(47.19185211883931, 280.689395648843));
        s.addPoint(new Vector(42.041157470124176, 283.8283998447455));
        s.addPoint(new Vector(36.483036031181115, 286.1763375390993));
        s.addPoint(new Vector(30.64691745306429, 287.6278349597152));
        s.addPoint(new Vector(24.579192901281445, 287.9533489272963));
        s.addPoint(new Vector(18.63074533738512, 286.72534951094593));
        s.addPoint(new Vector(13.470765775900318, 283.60910597552356));
        s.addPoint(new Vector(9.714547683878521, 278.8404534993486));
        s.addPoint(new Vector(7.410600965336741, 273.27653372573565));
        s.addPoint(new Vector(6.033890988341739, 267.3584311239511));
        s.addPoint(new Vector(5.111902632409368, 261.36431866505177));
        s.addPoint(new Vector(4.269460109022859, 255.31110000733122));
        s.addPoint(new Vector(3.4988473062702212, 249.30756189518337));
        s.addPoint(new Vector(2.80997816620949, 243.30556700208825));
        s.addPoint(new Vector(2.1968496402732427, 237.32004983366414));
        s.addPoint(new Vector(1.64500734427277, 231.3344668196832));
        s.addPoint(new Vector(1.1397534083490228, 225.30468634947346));
        s.addPoint(new Vector(0.6740461376229234, 219.24277108653808));
        s.addPoint(new Vector(0.24809399724249204, 213.2435162787296));
        s.addPoint(new Vector(-0.15255918866671436, 207.17541033661098));
        s.addPoint(new Vector(-0.5274796259034389, 201.09597755455673));
        s.addPoint(new Vector(-0.8765143269080369, 195.05986410746135));
        s.addPoint(new Vector(-1.2061150209089675, 188.99721760602023));
        s.addPoint(new Vector(-1.515838849713873, 182.9474557102511));
        s.addPoint(new Vector(-1.8054000676337552, 176.9478511963715));
        s.addPoint(new Vector(-2.0801205247108783, 170.90953075344487));
        s.addPoint(new Vector(-2.339048488688732, 164.8643715603364));
        s.addPoint(new Vector(-2.5812991133598473, 158.8444866430143));
        s.addPoint(new Vector(-2.810466281072749, 152.76091133368192));
        s.addPoint(new Vector(-3.024676894787163, 146.65142460204714));
        s.addPoint(new Vector(-3.2220659516074335, 140.55665490444665));
        s.addPoint(new Vector(-3.4008125002503817, 134.51942637826295));
        s.addPoint(new Vector(-3.5618871496716196, 128.47625098438442));
        s.addPoint(new Vector(-3.704515519754082, 122.38439504464341));
        s.addPoint(new Vector(-3.8243435940141097, 116.32429498852153));
        s.addPoint(new Vector(-3.9184034875508615, 110.2914724952119));
        s.addPoint(new Vector(-3.9826819800688895, 104.2300953560896));
        s.addPoint(new Vector(-4.009540441990993, 98.21985462645137));
        s.addPoint(new Vector(-3.9890291477148736, 92.10497275631161));
        s.addPoint(new Vector(-3.908092907810527, 86.01359098109498));
        s.addPoint(new Vector(-3.7623769934226914, 79.93382094622109));
        s.addPoint(new Vector(-3.58255921011056, 73.85062280397071));
        s.addPoint(new Vector(-3.377090825368896, 67.82318590528286));
        s.addPoint(new Vector(-3.1204875139732593, 61.73737419813061));
        s.addPoint(new Vector(-2.815976514296196, 55.70983870152139));
        s.addPoint(new Vector(-2.4665302633749064, 49.66846197751542));
        s.addPoint(new Vector(-2.082385887890723, 43.6646165018951));
        s.addPoint(new Vector(-1.6684795141033817, 37.67426818506301));
        s.addPoint(new Vector(-1.2263432458518793, 31.652141800772142));
        s.addPoint(new Vector(-0.7604293450215494, 25.612200095580633));
        s.addPoint(new Vector(-0.27447924710773464, 19.567159741335452));
        s.addPoint(new Vector(0.22789444995757435, 13.534347924807484));
        s.addPoint(new Vector(0.7424557681339934, 7.542715970657937));
        s.addPoint(new Vector(1.2748608743427496, 1.5133252834876032));
        s.addPoint(new Vector(1.825680181577951, -4.563861761776948));
        s.addPoint(new Vector(2.3816052891227315, -10.544055198706545));
        s.addPoint(new Vector(2.959490557864413, -16.59878110953038));
        s.addPoint(new Vector(3.5508991727712385, -22.603897980358198));
        s.addPoint(new Vector(4.175537422703172, -28.6547726321116));
        s.addPoint(new Vector(4.21375524701331, -29.015175976751465));
        s.addPoint(new Vector(4.21375524701331, -50.015175976751465));

        Path path = s.generatePath();

        barrelRacingPath = Arrays.asList(path);
        return barrelRacingPath;
    }

    public static List<Path> getBouncePath() {

        // STARTING POSITION: (40, 90)

        if (bouncePath != null) return bouncePath;

        PathGenerator pathOneGen = new PathGenerator(spacing, true);
        PathGenerator pathTwoGen = new PathGenerator(spacing, false);
        PathGenerator pathThreeGen = new PathGenerator(spacing, true);
        PathGenerator pathFourGen = new PathGenerator(spacing, false);

        pathOneGen.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        pathOneGen.setVelocities(maxVel, maxAccel, maxVelk);
        pathTwoGen.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        pathTwoGen.setVelocities(maxVel, maxAccel, maxVelk);
        pathThreeGen.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        pathThreeGen.setVelocities(maxVel, maxAccel, maxVelk);
        pathFourGen.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        pathFourGen.setVelocities(maxVel, maxAccel, maxVelk);

        pathOneGen.addPoint(new Vector(0.0, 0.0));
        pathOneGen.addPoint(new Vector(-0.0490684574469924, 6.066290633752942));
        pathOneGen.addPoint(new Vector(-0.3768927887120981, 12.0991185602795));
        pathOneGen.addPoint(new Vector(-1.2391986039301628, 18.06456825582245));
        pathOneGen.addPoint(new Vector(-2.8946444698040352, 23.90741217774206));
        pathOneGen.addPoint(new Vector(-5.504620353848537, 29.35281600129855));
        pathOneGen.addPoint(new Vector(-9.093994191244349, 34.18853025908359));
        pathOneGen.addPoint(new Vector(-13.540038225205535, 38.28386788877651));
        pathOneGen.addPoint(new Vector(-18.595683106555043, 41.59338555339673));
        pathOneGen.addPoint(new Vector(-24.039798383885767, 44.18832946685389));
        pathOneGen.addPoint(new Vector(-29.749969541724823, 46.18717635746947));
        pathOneGen.addPoint(new Vector(-35.611165735326864, 47.68105058461032));
        pathOneGen.addPoint(new Vector(-41.589881018652704, 48.75612886098277));
        pathOneGen.addPoint(new Vector(-47.5745795747053, 49.464306716833974));
        pathOneGen.addPoint(new Vector(-53.61436985360342, 49.86474507168168));
        pathOneGen.addPoint(new Vector(-59.62680765735581, 50.00135275242741));
        pathOneGen.addPoint(new Vector(-65.0, 50.0));
        pathTwoGen.addPoint(new Vector(-65.0, 50.0));
        pathTwoGen.addPoint(new Vector(-58.96976080384371, 50.12874351713947));
        pathTwoGen.addPoint(new Vector(-52.97849997631738, 50.72187639982957));
        pathTwoGen.addPoint(new Vector(-47.01299942353825, 51.73776131794068));
        pathTwoGen.addPoint(new Vector(-41.10178776791855, 53.04921352636403));
        pathTwoGen.addPoint(new Vector(-35.243512018622056, 54.572675677725314));
        pathTwoGen.addPoint(new Vector(-29.442820125205515, 56.25552825947656));
        pathTwoGen.addPoint(new Vector(-23.697347139855566, 58.06626882557285));
        pathTwoGen.addPoint(new Vector(-17.94502498041645, 60.00594896990161));
        pathTwoGen.addPoint(new Vector(-12.275292621692898, 62.032218699081355));
        pathTwoGen.addPoint(new Vector(-6.567678882679729, 64.18180620309572));
        pathTwoGen.addPoint(new Vector(-0.9819311005437328, 66.39102152152756));
        pathTwoGen.addPoint(new Vector(4.625637899893746, 68.71606670744332));
        pathTwoGen.addPoint(new Vector(10.202379786402958, 71.14040433764477));
        pathTwoGen.addPoint(new Vector(15.695173031194585, 73.64668146563329));
        pathTwoGen.addPoint(new Vector(21.1454648924355, 76.26341880524879));
        pathTwoGen.addPoint(new Vector(26.58045602465134, 79.02164533188183));
        pathTwoGen.addPoint(new Vector(31.918984220252327, 81.90452595738799));
        pathTwoGen.addPoint(new Vector(37.150527793928575, 84.93900837700782));
        pathTwoGen.addPoint(new Vector(42.228878848145314, 88.14720566753647));
        pathTwoGen.addPoint(new Vector(47.1864149605548, 91.63511708837322));
        pathTwoGen.addPoint(new Vector(51.867424158955686, 95.44164751768051));
        pathTwoGen.addPoint(new Vector(56.11040143405532, 99.70617448976364));
        pathTwoGen.addPoint(new Vector(59.54698483246489, 104.62747599988703));
        pathTwoGen.addPoint(new Vector(61.49860532311192, 110.3249381785044));
        pathTwoGen.addPoint(new Vector(61.897750151493796, 116.32766591434012));
        pathTwoGen.addPoint(new Vector(61.06602005818334, 122.31709279901986));
        pathTwoGen.addPoint(new Vector(57.45363404065105, 127.16397041534688));
        pathTwoGen.addPoint(new Vector(52.29202763863216, 130.35102994772382));
        pathTwoGen.addPoint(new Vector(46.683675134518865, 132.5306907046465));
        pathTwoGen.addPoint(new Vector(40.82987258201874, 134.16165579835385));
        pathTwoGen.addPoint(new Vector(34.9147567453998, 135.41991212549786));
        pathTwoGen.addPoint(new Vector(28.983302725379986, 136.41899529731725));
        pathTwoGen.addPoint(new Vector(22.941324816714626, 137.24057025251113));
        pathTwoGen.addPoint(new Vector(16.915264293154934, 137.90876920393788));
        pathTwoGen.addPoint(new Vector(10.906048540241471, 138.45502656175438));
        pathTwoGen.addPoint(new Vector(4.889233274472204, 138.9029718427809));
        pathTwoGen.addPoint(new Vector(-1.1734826914341454, 139.26973312776147));
        pathTwoGen.addPoint(new Vector(-7.216101213937208, 139.5625167885333));
        pathTwoGen.addPoint(new Vector(-13.278770712468884, 139.7923743957974));
        pathTwoGen.addPoint(new Vector(-19.28873644062591, 139.9641317451536));
        pathTwoGen.addPoint(new Vector(-25.364389369488975, 140.08674175135945));
        pathTwoGen.addPoint(new Vector(-31.39292784182109, 140.16236960589006));
        pathTwoGen.addPoint(new Vector(-37.41480573896612, 140.1960824813651));
        pathTwoGen.addPoint(new Vector(-43.4623895030214, 140.1914876468141));
        pathTwoGen.addPoint(new Vector(-49.47908325913326, 140.1526722083573));
        pathTwoGen.addPoint(new Vector(-55.50330146922042, 140.08630739229494));
        pathTwoGen.addPoint(new Vector(-61.54705080601363, 140.01393292637167));
        pathTwoGen.addPoint(new Vector(-65.0, 140.0));
        pathThreeGen.addPoint(new Vector(-65.0, 140.0));
        pathThreeGen.addPoint(new Vector(-58.91138659757095, 139.98475587047636));
        pathThreeGen.addPoint(new Vector(-52.81790697658076, 139.92439312785388));
        pathThreeGen.addPoint(new Vector(-46.79806970521463, 139.847384348294));
        pathThreeGen.addPoint(new Vector(-40.73092729276635, 139.78594502518936));
        pathThreeGen.addPoint(new Vector(-34.66857189398536, 139.76455006415844));
        pathThreeGen.addPoint(new Vector(-28.583842300975107, 139.8005714800021));
        pathThreeGen.addPoint(new Vector(-22.547493274191567, 139.9072295459141));
        pathThreeGen.addPoint(new Vector(-16.5028059403146, 140.09791410541732));
        pathThreeGen.addPoint(new Vector(-10.448692228005328, 140.38671778884083));
        pathThreeGen.addPoint(new Vector(-4.367071140078167, 140.7910053205367));
        pathThreeGen.addPoint(new Vector(1.6716830351938512, 141.32413162082992));
        pathThreeGen.addPoint(new Vector(7.697232347306084, 142.00970757842882));
        pathThreeGen.addPoint(new Vector(13.642007345729041, 142.86515748160113));
        pathThreeGen.addPoint(new Vector(19.622216515631024, 143.94366953928403));
        pathThreeGen.addPoint(new Vector(25.546981297349973, 145.28090033701926));
        pathThreeGen.addPoint(new Vector(31.396683681828492, 146.93985836284145));
        pathThreeGen.addPoint(new Vector(37.11549672250226, 149.00104981745278));
        pathThreeGen.addPoint(new Vector(42.60102892593636, 151.56449813709818));
        pathThreeGen.addPoint(new Vector(47.6965413410511, 154.7503646932369));
        pathThreeGen.addPoint(new Vector(52.23369312092535, 158.74262134855135));
        pathThreeGen.addPoint(new Vector(55.83175467307939, 163.54960220419576));
        pathThreeGen.addPoint(new Vector(58.273108941706994, 169.10343733469722));
        pathThreeGen.addPoint(new Vector(59.53709736843729, 175.0018874002564));
        pathThreeGen.addPoint(new Vector(59.969332286168424, 181.09298336669048));
        pathThreeGen.addPoint(new Vector(60.00845448959373, 187.13852502428506));
        pathThreeGen.addPoint(new Vector(59.81608784279268, 193.14129642261287));
        pathThreeGen.addPoint(new Vector(58.88195407342289, 199.10552064371612));
        pathThreeGen.addPoint(new Vector(56.71862934115521, 204.79943768939285));
        pathThreeGen.addPoint(new Vector(53.28247577450625, 209.7363116927081));
        pathThreeGen.addPoint(new Vector(48.8087009944002, 213.83841972635003));
        pathThreeGen.addPoint(new Vector(43.741062018932524, 217.10767974979));
        pathThreeGen.addPoint(new Vector(38.30515238714304, 219.7374112317184));
        pathThreeGen.addPoint(new Vector(32.612208845185236, 221.88489721066423));
        pathThreeGen.addPoint(new Vector(26.867661948241107, 223.61954048180883));
        pathThreeGen.addPoint(new Vector(20.942251894268566, 225.07446362253438));
        pathThreeGen.addPoint(new Vector(15.046950420460178, 226.26154915770155));
        pathThreeGen.addPoint(new Vector(9.069154646298855, 227.25254972320408));
        pathThreeGen.addPoint(new Vector(3.0842231620662517, 228.06741491336163));
        pathThreeGen.addPoint(new Vector(-2.947710156100854, 228.73681725413888));
        pathThreeGen.addPoint(new Vector(-8.960645967170322, 229.27312963554328));
        pathThreeGen.addPoint(new Vector(-14.995805187075064, 229.69594248554154));
        pathThreeGen.addPoint(new Vector(-21.08268857469912, 230.01788747431237));
        pathThreeGen.addPoint(new Vector(-27.129393900096147, 230.24375142766485));
        pathThreeGen.addPoint(new Vector(-33.21791349152144, 230.38447837730342));
        pathThreeGen.addPoint(new Vector(-39.27602230322154, 230.4443457543266));
        pathThreeGen.addPoint(new Vector(-45.318694578715196, 230.42925066291974));
        pathThreeGen.addPoint(new Vector(-51.374984717999126, 230.34327186585597));
        pathThreeGen.addPoint(new Vector(-57.380665661989525, 230.19373812537935));
        pathThreeGen.addPoint(new Vector(-63.41926138641625, 230.01461521014494));
        pathThreeGen.addPoint(new Vector(-65.0, 230.0));
        pathFourGen.addPoint(new Vector(-65.0, 230.0));
        pathFourGen.addPoint(new Vector(-58.979318402365664, 230.68352743185568));
        pathFourGen.addPoint(new Vector(-53.04748823991781, 231.87008974861027));
        pathFourGen.addPoint(new Vector(-47.132148878267714, 233.22397993042125));
        pathFourGen.addPoint(new Vector(-41.29819468795816, 234.6843081349155));
        pathFourGen.addPoint(new Vector(-35.469841576573856, 236.26643464498278));
        pathFourGen.addPoint(new Vector(-29.646753118122675, 237.99108347382793));
        pathFourGen.addPoint(new Vector(-23.920198689705444, 239.87461923164204));
        pathFourGen.addPoint(new Vector(-19.291463563496734, 241.0036034920618));
        pathFourGen.addPoint(new Vector(-12.797433500916213, 244.55457430097226));
        pathFourGen.addPoint(new Vector(-8.340329350984746, 247.17468517920588));
        pathFourGen.addPoint(new Vector(-3.340329350984746, 250.17468517920588));

        Path pathOne = pathOneGen.generatePath();
        Path pathTwo = pathTwoGen.generatePath();
        Path pathThree = pathThreeGen.generatePath();
        Path pathFour = pathFourGen.generatePath();

        bouncePath = Arrays.asList(pathOne, pathTwo, pathThree, pathFour);
        return bouncePath;
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
