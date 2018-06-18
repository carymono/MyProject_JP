import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.core.converters.ArffSaver;
import java.io.File;
import java.io.IOException;

public class CSV2Arff {
	
	public static void main(String []args) throws Exception {
	
		CSVLoader loader = new CSVLoader();
		loader.setSource(new File(""));
		Instances data = loader.getDataSet();
		
		ArffSaver saver = new ArffSaver();
		saver.setInstances(data);
		
		saver.setFile(new File(""));
		saver.writeBatch();
		
	}
}
