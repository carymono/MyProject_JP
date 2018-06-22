import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.LinearRegression;


import java.io.File;
import java.io.IOException;
import java.util.Random;


public class CrossValidation {
	static String fileLocation = "./miyazaki94.arff";

	public static void main(String[]args) {
		try {
			//Load Data from the file
			DataSource source = new DataSource(fileLocation);
			//Set instance from source data.
			Instances dataset = source.getDataSet();
			//Set the index of the data set to the last attribute
			dataset.setClassIndex(dataset.numAttributes()-1);

			System.out.println("---------------------------------------");
			System.out.println("------------Do Linear Regression--------------");

			//Call for Linear Regression model to train the model with the data.
			LinearRegression lr = new LinearRegression();
			lr.buildClassifier(dataset);

			System.out.println(lr);

			System.out.println("-----------Do Evaluation Model---------------------------");

			//Bring the dataset to the evaluation.
			Evaluation eval = new Evaluation(dataset);
			eval.evaluateModel(lr, dataset);
			System.out.println(eval.toSummaryString("Evaluation result: \n", false));

			System.out.println("-----------Do 10 folds cross validation model---------------------------");

			Evaluation crossEval = new Evaluation(dataset);
			Random rand = new Random(1);
			int folds = 10;
			crossEval.crossValidateModel(lr, dataset, folds, rand);
			System.out.println(crossEval.toSummaryString("10 folds Cross Validation result: \n", false));

			System.out.println("--------------------------------------");
			System.out.println();
			
			System.out.println("Or maybe this way....");
			
			//this is just a test from the youtube WEKA API tutorial I try to find the way to handle the numeric class
			DataSource source2 = new DataSource("./iris.arff");
			Instances dataset2 = source2.getDataSet();
			dataset2.setClassIndex(dataset2.numAttributes()-1);
			//create the classifier
			NaiveBayes nb = new NaiveBayes();
			int seed = 1;
			int folds2 = 15;
			//randomize data
			Random rand2 = new Random(seed);
			//create random dataset2
			Instances randData = new Instances(dataset2);
			randData.randomize(rand2);
			//stratify
			if(randData.classAttribute().isNominal()) {
				randData.stratify(folds2);
			}
			
			System.out.println("Saysomething here");
			//perform cross-validation
			
			for (int n=0 ; n < folds2 ; n++) {
				Evaluation eval2 = new Evaluation(randData);

				//get the folds
				Instances train = randData.trainCV(folds2, n);
				Instances test = randData.testCV(folds2, n);
				//build and evaluate classifier
				nb.buildClassifier(train);
				eval2.evaluateModel(nb, test);
				
				//output evaluation
				System.out.println(eval2.toMatrixString("matrix for folds: " + (n+1) + "/" + folds2));
				
			}
			System.out.println("End of the Program");

		} catch (Exception e) {
			System.out.println("Error occur: " + e);
		}

	}
}

/* 
 * Note to do:
 * Show mean absolute error with cross validation.
 * Try to use another modeling method.
 * SVM - has several parameters to be tuned.
 * Compare performance between SVM and LinearRegression.
 * 
 * 1. Read miyazaki94.arff data from Java code (you can find a function or a class in Weka for that purpose)
 * 2. Call LinearRegression of Weka from your Java code to train a model with the data.
 * 3. Estimate effort values of the data with the trained model
 * 4. Calculate absolute errors between the estimate and actual effort values.
 * At 2 and 3, it is better to use cross-validation if you can (this is not a mandatory task for the present)
 * 
 */




