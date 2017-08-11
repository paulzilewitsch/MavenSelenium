$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("MyFeatures.feature");
formatter.feature({
  "line": 1,
  "name": "Test Commerzbank Baufinanzierung",
  "description": "",
  "id": "test-commerzbank-baufinanzierung",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Test Commerzbank Calculator",
  "description": "",
  "id": "test-commerzbank-baufinanzierung;test-commerzbank-calculator",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "Firefox is open, web application is startet and web formular is given",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "All required Fields are filled and user click calculation button",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "User get Calculation and application should be closed",
  "keyword": "Then "
});
formatter.match({
  "location": "Step01_Calculator.Open_firefox_and_start_application()"
});
formatter.result({
  "duration": 15059514059,
  "status": "passed"
});
formatter.match({
  "location": "Step01_Calculator.Fill_all_required_fields()"
});
formatter.result({
  "duration": 15319877946,
  "status": "passed"
});
formatter.match({
  "location": "Step01_Calculator.Get_Calculation()"
});
formatter.result({
  "duration": 750199117,
  "status": "passed"
});
});