Feature: Test Baufi Formular

	Scenario: Baufi Formular ausfüllen
	Given Firefox is open, web application is started and web formular is given
	When All required Fields are filled and user click calculation button
	Then User get Calculation and application should be closed