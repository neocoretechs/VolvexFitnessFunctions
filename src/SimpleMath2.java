import com.neocoretechs.volvex.Chromosome;
import com.neocoretechs.volvex.fitnessfunctions.FitnessFunction;
import com.neocoretechs.volvex.worlds.RelatrixWorld;
import com.neocoretechs.volvex.worlds.World;

/**
 * Expression of fitness function as chromosome function. When using the chromosome function in this way we nullify the functions and
 * depth and provide the means to store the guid as a simple name for semantic retrieval from deep store.<p/>
 * In the designated world, we instantiate this class and provide access to it through the computeRawFitness method. It is presumed that the
 * return type will always be a float.
 * @author groff
 *
 */
public class SimpleMath2 extends FitnessFunction {
	private static final long serialVersionUID = -328949489979118002L;
	int l, k;
 
	/**
	 * @param guid
	 */
	public SimpleMath2(World w, String guid) {
		super(w, guid);
	}
	/**
	 * @param argTypes
	 * @param returnType
	 */
	public SimpleMath2(World w, Class[] argTypes, Class returnType) {
		super(w,argTypes, returnType);
	}

	public SimpleMath2() {}
	
	// put test specific stuff here
	/**
	 * Add one
	 */
	@Override
	public Object execute(Chromosome ind) {
	             int hits = 0;
	             int test = 0;
	             boolean[][] results = new boolean[(int)((RelatrixWorld)world).MaxSteps][(int)((RelatrixWorld)world).TestsPerStep];
	             Object[] argVals = new Object[1];
	             // unit test
	             // each time this individual passes, up the hits
	             for(int i = 0; i < ((RelatrixWorld)world).MaxSteps; i++) {
	                 int step = 0;
	                     //vars[0].set(new Integer(i));
	                 	 argVals[0] = new Integer(i);
	                     int j = ind.execute_int(argVals);
	                     //System.out.println(i+" "+j);
	                     if( j == i+1 ) {
	                    	 ++hits;
	                       	 results[test][step] = true;
	                     } else
	                    	 results[test][step] = false;
	                 ++test;
	             }
	             //System.out.println("Hits: "+hits);
	             float rawFit = (((RelatrixWorld)world).MinRawFitness - (float)(hits));
	   	      	 ((RelatrixWorld)world).showTruth(ind, rawFit, results);
	             return rawFit;
	}

}
