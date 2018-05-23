package ac.id.ukdw.juan.knnanimal.service;

import ac.id.ukdw.juan.knnanimal.entity.AnimalAttributes;
import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.core.*;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Juan on 5/18/18.
 */
@Service
public class KNNService {
    Classifier ibk = new IBk();
    Instances data;
    Instance inst;
    Instances dataPredict;


    KNNService () throws Exception {
        this.training(1);

        Attribute animal_name = new Attribute("animal_name");
        Attribute hair = new Attribute("hair");

        Attribute feathers = new Attribute("feathers");
        Attribute eggs = new Attribute("eggs");
        Attribute milk = new Attribute("milk");
        Attribute airborne = new Attribute("airborne");

        Attribute aquatic = new Attribute("aquatic");
        Attribute predator = new Attribute("predator");
        Attribute toothed = new Attribute("toothed");
        Attribute backbone = new Attribute("backbone");
        Attribute breathes = new Attribute("breathes");
        Attribute venomous = new Attribute("venomous");

        Attribute fins = new Attribute("fins");
        Attribute legs = new Attribute("legs");
        Attribute tail = new Attribute("tail");
        Attribute domestic = new Attribute("domestic");
        Attribute catsize = new Attribute("catsize");
        FastVector fvNominalVal = new FastVector(7);
        fvNominalVal.addElement("1");
        fvNominalVal.addElement("2");
        fvNominalVal.addElement("3");
        fvNominalVal.addElement("4");
        fvNominalVal.addElement("5");
        fvNominalVal.addElement("6");
        fvNominalVal.addElement("7");
        Attribute classAttribute = new Attribute("class", fvNominalVal);
        ArrayList<Attribute> attributes = new ArrayList<Attribute>(data.numAttributes());
        attributes.add(animal_name);
        attributes.add(hair);
        attributes.add(feathers);
        attributes.add(eggs);
        attributes.add(milk);
        attributes.add(airborne);
        attributes.add(aquatic);
        attributes.add(predator);
        attributes.add(toothed);
        attributes.add(backbone);
        attributes.add(breathes);
        attributes.add(venomous);
        attributes.add(fins);
        attributes.add(legs);
        attributes.add(tail);
        attributes.add(domestic);
        attributes.add(catsize);
        attributes.add(classAttribute);

        inst = new DenseInstance(data.numAttributes());
        dataPredict = new Instances("zoo",attributes,1);
        dataPredict = Filter.useFilter(dataPredict, filterRemoveFirstAttribute(dataPredict));
        dataPredict.setClassIndex(data.numAttributes() - 1);

    }
    private Instances getDataSet(String fileName) throws IOException {
        /**
         * we can set the file i.e., loader.setFile("finename") to load the data
         */
        int classIdx = 1;
        /** the arffloader to load the arff file */
        ArffLoader loader = new ArffLoader();
        /** load the traing data */
        loader.setSource(KNNService.class.getResourceAsStream("/static/" + fileName));
        /**
         * we can also set the file like loader3.setFile(new
         * File("test-confused.arff"));
         */
        //loader.setFile(new File(fileName));
        Instances dataSet = loader.getDataSet();
        /** set the index based on the data given in the arff files */
        dataSet.setClassIndex(classIdx);
        return dataSet;
    }

    private Remove filterRemoveFirstAttribute(Instances dataBefore) throws Exception {
        String[] options = new String[2];
        options[0] = "-R";
        options[1] = "1";
        Remove rm = new Remove();
        rm.setOptions(options);
        rm.setInputFormat(dataBefore);
        return  rm;

    }

    private void training(Integer k) throws Exception {
        Instances dataBefore = getDataSet("zoo.arff");

        data = Filter.useFilter(dataBefore, filterRemoveFirstAttribute(dataBefore));
        data.setClassIndex(data.numAttributes() - 1);
        ibk = new IBk(k);
        ibk.buildClassifier(data);
        System.out.println(ibk);

        Evaluation eval = new Evaluation(data);
        eval.evaluateModel(ibk, data);
        /** Print the algorithm summary */
        System.out.println("** KNN Demo  **");
        System.out.println(eval.toSummaryString());
        System.out.println(eval.toClassDetailsString());
        System.out.println(eval.toMatrixString());
    }

    public String test(AnimalAttributes animalAttributes) throws Exception {

        inst.setValue(dataPredict.attribute(0), animalAttributes.getHair());
        inst.setValue(dataPredict.attribute(1), animalAttributes.getFeathers());
        inst.setValue(dataPredict.attribute(2), animalAttributes.getEggs());
        inst.setValue(dataPredict.attribute(3), animalAttributes.getMilk());
        inst.setValue(dataPredict.attribute(4), animalAttributes.getAirborne());
        inst.setValue(dataPredict.attribute(5), animalAttributes.getAquatic());
        inst.setValue(dataPredict.attribute(6), animalAttributes.getPredator());
        inst.setValue(dataPredict.attribute(7), animalAttributes.getToothed());
        inst.setValue(dataPredict.attribute(8), animalAttributes.getBackbone());
        inst.setValue(dataPredict.attribute(9), animalAttributes.getBreathes());
        inst.setValue(dataPredict.attribute(10),animalAttributes.getVenomous());
        inst.setValue(dataPredict.attribute(11),animalAttributes.getFins());
        inst.setValue(dataPredict.attribute(12),animalAttributes.getLegs());
        inst.setValue(dataPredict.attribute(13),animalAttributes.getTail());
        inst.setValue(dataPredict.attribute(14),animalAttributes.getDomestic());
        inst.setValue(dataPredict.attribute(15),animalAttributes.getCatsize());
        dataPredict.add(0, inst);
        data.add(inst);



//        Instance instancePredict = dataPredict.instance(0);
        Instance instancePredict = data.lastInstance();
        Double result = ibk.classifyInstance(instancePredict);

        Integer resultInteger = result.intValue();
        String namaHewan = animalAttributes.getAnimal_name();
        String resultString = "Undefined";
        switch (resultInteger) {
            case 0 :
                resultString = "Mammal";
                break;
            case 1 :
                resultString = "Bird";
                break;
            case 2:
                resultString = "Reptile";
                break;
            case 3:
                resultString = "Fish";
                break;
            case 4:
                resultString = "Amphibian";
                break;
            case 5:
                resultString = "Bug";
                break;
            case 6:
                resultString = "Invertebrate";
                break;
            default:
                resultString = "Undefined";
                break;

        }

        return namaHewan + " dikategorikan " + resultString;
//        System.out.println(ibk.classifyInstance(instancePredict));
    }

    public String eval() throws Exception {
        Evaluation eval = new Evaluation(data);
        ibk = new IBk(1);
        ibk.buildClassifier(data);
        eval.evaluateModel(ibk, data);
        String result = "** KNN Demo  **";
        result += System.lineSeparator() + eval.toSummaryString();
        result += System.lineSeparator() + eval.toClassDetailsString();
        result += System.lineSeparator() + eval.toMatrixString();


        return result;
    }
}
