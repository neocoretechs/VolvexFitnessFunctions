import com.neocoretechs.volvex.Chromosome;
import com.neocoretechs.volvex.Function;
import com.neocoretechs.volvex.fitnessfunctions.FitnessFunction;
import com.neocoretechs.volvex.functions.Variable;
import com.neocoretechs.volvex.objects.Matrix3x3;
import com.neocoretechs.volvex.objects.MatrixNxN;
import com.neocoretechs.volvex.objects.Strings;
import com.neocoretechs.volvex.worlds.RelatrixWorld;
import com.neocoretechs.volvex.worlds.World;

public class Imager1 extends FitnessFunction {
	private static final long serialVersionUID = -6456050967277445199L;
	  //Variable[] vars = new Variable[8];
	int[][] imagedata1 = {
			{0,0,0,0,0},
			{0,0,0,0,0},
			{0,0,1,0,0},
			{0,0,0,0,0},
			{0,0,0,0,0}
	};
	int[][] imagedata2 = {
			{0,0,0,0,0},
			{0,0,0,0,0},
			{0,0,0,0,0},
			{0,0,0,0,0},
			{0,0,0,0,0}
	};
	MatrixNxN image1 = new MatrixNxN(5, imagedata1);
	MatrixNxN image2 = new MatrixNxN(5, imagedata2);

	public Imager1(World w) {
		super(w);
		// TODO Auto-generated constructor stub
	}

	public Imager1(World w, String guid) {
		super(w, guid);
		// TODO Auto-generated constructor stub
	}

	public Imager1(World w, Class[] argTypes, Class returnType) {
		super(w, argTypes, returnType);
		// TODO Auto-generated constructor stub
	}

	public Imager1(World w, int size, Function[] functionSet, Class[] argTypes, Class returnType) {
		super(w, size, functionSet, argTypes, returnType);
		// TODO Auto-generated constructor stub
	}

	public Imager1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute(Chromosome ind) {
	
			int sum = 0;
			MatrixNxN result;
			
			int[][] f1 = {{0,0,0},{0,0,0},{1,1,1}};
			int[][] f2 = {{0,0,0},{1,1,1},{0,0,0}};
			int[][] f3 = {{1,1,1},{0,0,0},{0,0,0}};
			int[][] f4 = {{1,0,0},{1,0,0},{1,0,0}};
			int[][] f5 = {{0,1,0},{0,1,0},{0,1,0}};
			int[][] f6 = {{0,0,1},{0,0,1},{0,0,1}};
			int[][] f0 = {{0,0,0},{0,0,0},{0,0,0}};

			Variable.set("M0", new Matrix3x3(f1));
			Variable.set("M1", new Matrix3x3(f2));
			Variable.set("M2", new Matrix3x3(f3));
			Variable.set("M3", new Matrix3x3(f4));
			Variable.set("M4", new Matrix3x3(f5));
			Variable.set("M5", new Matrix3x3(f6));
			Variable.set("M6", new Matrix3x3(f0));
	 
	        ((RelatrixWorld)world).setStepFactors(1,2);
	        int test = 0;
	        boolean[][] results = new boolean[(int) ((RelatrixWorld)world).MaxSteps][(int) ((RelatrixWorld)world).TestsPerStep];
	        Object[] argVals = new Object[3];
	        // unit test
	        // each time this individual passes, up the hits
	        for(int s = 0; s < ((RelatrixWorld)world).MaxSteps; s++) {
	            int step = 0;
	    		argVals[0] = image1;
	    		argVals[1] = Variable.get("M0");
	    		argVals[2] = new Strings();
	      		//argVals[1][0] = image1;
	    		//argVals[1][1] = Variable.get("M1");
	    		//argVals[1][2] = new Strings();
	      		//argVals[2][0] = image1;
	    		//argVals[2][1] = Variable.get("M2");
	    		//argVals[2][2] = new Strings();
	      		//argVals[3][0] = image1;
	    		//argVals[3][1] = Variable.get("M3");
	    		//argVals[3][2] = new Strings();
	    		result = (MatrixNxN)ind.execute_object(argVals);
	    		for (int i=0; i<5; i++)
	    			for (int j=0; j<5; j++)
	    				if(result.data.length == 5 )
	    				if (i>0 || j>0) {
	    					sum += Math.abs(result.data[i][j]);
	    				} else {
	    					sum += 200*Math.abs(5-result.data[i][j]);
	    				}
	    		++step;
	       		argVals[0] = image2;
	    		argVals[1] = Variable.get("M0");
	    		argVals[2] = new Strings();
	      		//argVals[1][0] = image2;
	    		//argVals[1][1] = Variable.get("M1");
	    		//argVals[1][2] = new Strings();
	      		//argVals[2][0] = image2;
	    		//argVals[2][1] = Variable.get("M2");
	    		//argVals[2][2] = new Strings();
	      		//argVals[3][0] = image2;
	    		//argVals[3][1] = Variable.get("M3");
	    		//argVals[3][2] = new Strings();
	    		result = (MatrixNxN)ind.execute_object(argVals);
	    		for (int i=0; i<5; i++)
	    			for (int j=0; j<5; j++)
	    				if( result.data.length == 5)
	    				if (i>0 || j>0) {
	    					sum += Math.abs(result.data[i][j]);
	    				} else {
	    					sum += 200*Math.abs(-5-result.data[i][j]);
	    				}
	            ++test;
	        }
	        ((RelatrixWorld)world).showTruth(ind, sum, results);
	        //System.out.println("Hits: "+sum);
			return (float)sum;
	  }

}
