package us.mn.state.health.lims.testconfiguration.action;

import java.util.ArrayList;
import java.util.List;

import us.mn.state.health.lims.common.services.TypeOfSampleService;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

public class PanelTests {
	private IdValuePair panelIdValuePair;
	private IdValuePair sampleTypeIdValuePair;
	private List<IdValuePair> tests = new ArrayList<IdValuePair>();
	private List<IdValuePair> availableTests = new ArrayList<IdValuePair>();
	
	public PanelTests(IdValuePair panelPair) {
		this.panelIdValuePair = panelPair;
		List<TypeOfSample> typeOfSamples = TypeOfSampleService.getTypeOfSampleForPanelId(panelPair.getId());
		
		if (typeOfSamples != null && typeOfSamples.size() > 0) {
			TypeOfSample typeOfSample = (TypeOfSample) typeOfSamples.get(0);
			this.sampleTypeIdValuePair = new IdValuePair(typeOfSample.getId(), typeOfSample.getLocalizedName());
		}
	}
	
	public PanelTests() {
	
	}
	

	public IdValuePair getPanelIdValuePair() {
		return panelIdValuePair;
	}

	public void setPanelIdValuePair(IdValuePair panelIdValuePair) {
		this.panelIdValuePair = panelIdValuePair;
	}


	public IdValuePair getSampleTypeIdValuePair() {
		return sampleTypeIdValuePair;
	}

	public void setSampleTypeIdValuePair(IdValuePair sampleTypeIdValuePair) {
		this.sampleTypeIdValuePair = sampleTypeIdValuePair;
	}

	public List<IdValuePair> getTests() {
		return tests;
	}

	public void setTests(List<IdValuePair> tests) {
		this.tests = tests;
		List<Test> allTests = TypeOfSampleService.getAllTestsBySampleTypeId(this.sampleTypeIdValuePair.getId());
		for (Test test : allTests) {
			this.availableTests.add(new IdValuePair(test.getId(), test.getLocalizedName()));
		}
		
	}

	public List<IdValuePair> getAvailableTests() {
		return availableTests;
	}

	public void setAvailableTests(List<IdValuePair> availableTests) {
		this.availableTests = availableTests;
	}
}
