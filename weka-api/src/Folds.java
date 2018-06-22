import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.LinearRegression;


import java.io.File;
import java.io.IOException;
import java.util.Random;
public class Folds {
	public static void main (String[]args){
		try {
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
		}
		catch(Exception e){

		}
	}
}
