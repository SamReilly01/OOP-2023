package ie.tudublin;

public class Main
{

	public static void bugZap()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new BugZap());
    }
	
	public static void main(String[] args)
	{
		bugZap();
	}
	
}