import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.functions.LinearRegression;


import java.io.File;
import java.io.IOException;


public class CrossValidation {
	static String fileLocation = "D:\\Weka Jar\\weka-3-8-2\\data\\miyazaki94.arff";

	public static void main(String[]args) {
		try {
			
			
			DataSource source = new DataSource("D:\\Weka Jar\\weka-3-8-2\\data\\miyazaki94.arff");
			Instances dataset = source.getDataSet();
			
			System.out.println(dataset);		
			dataset.setClassIndex(dataset.numAttributes()-1);
			
			System.out.println("---------------------------------------");

			LinearRegression lr = new LinearRegression();
			lr.buildClassifier(dataset);

			System.out.println(lr);
			
			
		} catch (Exception e) {
		}

	}
}

//Note to do:
//Show mean absolute error with cross validation.
//Try to use another modeling method.
//SVM - has several parameters to be tuned.
//Compare performance between SVM and LinearRegression.

