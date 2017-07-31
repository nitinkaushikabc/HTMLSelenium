package com.rsystems.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReportGeneration {

	final String FILENAME = System.getProperty("user.dir") + "/ExecutionReports/dashboard.html";

	public void dashBoardGenerator() throws IOException {
		File folder = new File(System.getProperty("user.dir") + "/ExecutionReports");
		String optionValues = generateOptionsValue(folder);
		createDashboardHtml(optionValues);
	}

	private String generateOptionsValue(final File folder) throws IOException {

		String buildVersion = null;
		buildVersion = System.getProperty("BuildVersion");
		if (buildVersion == null || buildVersion.contentEquals("")) {
			buildVersion = TestInitization.getUpdatedProptiesFile().getProperty("BuildVersion");
		}

		String optionValues = "";
		for (File folder1 : folder.listFiles()) {

			if (folder1.isDirectory()) {
				optionValues = optionValues.concat("<option value = './" + folder1.getName() + "/index.html'>BuildVer_"
						+ buildVersion + "_" + folder1.getName() + "</option>\n");
			}
		}
		return optionValues;

	}

	private void createDashboardHtml(String optionValues) throws IOException {
		FileWriter fw = null;
		BufferedWriter bw = null;
		String dashboardHtml = "<!DOCTYPE html>" + "<html lang='en'><head><style type'text/css'>"
				+ "#main_iframe {position:fixed;height:100%;width:100%;top:90px;left:10px;right:10px;bottom:10px; z-index:1}nav "
				+ "{position:fixed; left:0px; top:0px; bottom:0px; width:160px; background:#333; color:#fff; z-index:2}</style><meta charset='utf-8'>"
				+ " <meta http-equiv='X-UA-Compatible' content='IE=edge'>"
				+ "<meta name='viewport' content='width=device-width, initial-scale=1'>"
				+ "<meta name='description' content=''>" + "<meta name='author' content=''>"

				+ " <meta name='viewport' content='width=device-width, initial-scale=1'>" + "<style>" + "th {"
				+ " border-bottom: 1px solid #d6d6d6;}" +

				"tr:nth-child(even) {" + "background: #e9e9e9;" + "}" + "</style>"
				+ "<title>Automation Dashboard</title>" + "</head>"

				+ "<base target='_blan'>" +

				"<iframe id='main_iframe'" + "src='" + TestInitization.currentExecutionReportPath + "\\index.html'>"
				+ "</iframe>"

				+ "<body>"

				+ "<main>" + " <div class='container content-container'>"

				+ "      <label class='col-md-4' id='dropdownLabel' div style='position: fixed; bottom: 100%; top: 10px; width:20%;'> <font color='#026c97'>Select Release Version</font></label>"

				+ "      <div class='col-md-4' id='dropdownDiv' div style='position: fixed; bottom: 100%; top: 40px; width:20%; >"
				+ "<form>" + " <fieldset>" + " <div class='form-group'>" + "<form name='change'>"
				+ "  <SELECT NAME='options' id='ddMainMenu'>" + "<option selected = 'true'> Select Report </option>"

				+ optionValues

				+ "</SELECT>" + " </form> " + "</div>" + "</fieldset>" + " </form>" + "</div>" + " </div>" + "</main>"
				+ "<script>"

				+ " var urlmenu = document.getElementById('ddMainMenu');" + " urlmenu.onchange = function() {"

				+ "document.getElementById('main_iframe').src = this.options[this.selectedIndex].value;" + "};"

				+ "</script>" + "</body>" + "</html>";

		try {
			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(dashboardHtml);
		} finally {

			if (bw != null)
				bw.close();
			if (fw != null)
				fw.close();
		}

	}
}
