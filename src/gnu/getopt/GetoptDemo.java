package gnu.getopt;

import gnu.getopt.LongOpt;
import gnu.getopt.Getopt;

/*
 * This sample code was written by Aaron M. Renn and is a demonstration
 * of how to utilize some of the features of the GNU getopt package.  This
 * sample code is hereby placed into the public domain by the author and
 * may be used without restriction.
 */

public class GetoptDemo {

	public static void main(String[] args) {
		int c;
		String arg;
		LongOpt[] longopts = new LongOpt[3];
		//
		StringBuffer sb = new StringBuffer();
		longopts[0] = new LongOpt("help", LongOpt.NO_ARGUMENT, null, 'h');
		longopts[1] = new LongOpt("outputdir", LongOpt.REQUIRED_ARGUMENT, sb,'o');
		longopts[2] = new LongOpt("maximum", LongOpt.OPTIONAL_ARGUMENT, null, 2);
		//
		Getopt g = new Getopt("testprog", args, "-:bc::d:o:W;", longopts);
		g.setOpterr(false); // We'll do our own error handling
		//
		while ((c = g.getopt()) != -1)
			switch (c) {
			case 0:
				arg = g.getOptarg();
				System.out.println("Got long option with value '"
						+ (char) (new Integer(sb.toString())).intValue()
						+ "' with argument " + ((arg != null) ? arg : "null"));
				break;
			//
			case 1:
				System.out.println("I see you have return in order set and that "
								+ "a non-option argv element was just found "
								+ "with the value '" + g.getOptarg() + "'");
				break;
			//
			case 'o':
				arg = g.getOptarg();
				System.out.println("I know this, but pretend I didn't");
				System.out.println("We picked option "
						+ longopts[g.getLongind()].getName() + " with value "
						+ ((arg != null) ? arg : "null"));
				break;
			//
			case 'b':
				System.out.println("You picked plain old option " + (char) c);
				break;
			//
			case 'c':
			case 'd':
				arg = g.getOptarg();
				System.out.println("You picked option '" + (char) c
						+ "' with argument " + ((arg != null) ? arg : "null"));
				break;
			//
			case 'h':
				System.out.println("I see you asked for help");
				break;
			//
			case 'W':
				System.out
						.println("Hmmm. You tried a -W with an incorrect long "
								+ "option name");
				break;
			//
			case ':':
				System.out.println("Doh! You need an argument for option "
						+ (char) g.getOptopt());
				break;
			//
			case '?':
				System.out.println("The option '" + (char) g.getOptopt()
						+ "' is not valid");
				break;
			//
			default:
				System.out.println("getopt() returned " + c);
				break;
			}
		//
		System.out.println(sb+" good");
		for (int i = g.getOptind(); i < args.length; i++)
			System.out.println("Non option argv element: " + args[i] + "\n");
	}

} // Class GetoptDemo

