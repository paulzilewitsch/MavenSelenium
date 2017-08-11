Feature: Test Commerzbank Baufinanzierung

	Scenario: Test Commerzbank Calculator
	Given Firefox is open, web application is startet and web formular is given
	When All required Fields are filled and user click calculation button
	Then User get Calculation and application should be closed