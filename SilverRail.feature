Feature: Search stations

@tag1
Scenario: Search nearby stations by a VALID postcode
Given I have turned on my GPS
	And I have opened my Railway Stations app
When I enter the VALID post code I am in
	And I tap the Search Stations button
Then I should see nearest Railway stations in the map with the distance


Scenario: Search nearby stations by an INVALID postcode
Given I have turned on my GPS
	And I have opened my Railway Stations app
When I enter the INVALID post code
	And I tap the Search Stations button
Then I should see an error saying the post code is invalid
	And I should be able to re-enter the post code and search

Scenario: Search nearby stations accuracy by the post code
Given I have searched for nearby stations using the post code in 'location one'
	Then I note down the nearby stations list returned
	And Go to 'location two' and search for nearby stations using the same post code
When I come back to 'location one' and search for nearby stations using the same post code
Then I should get the same nearby stations list which I got at the first occassion


Scenario: Search nearby stations - check each search result
Given I have searched for nearby stations using a valid post code
	And I have received a list of nearby stations
	Then I check the actual distance to each station in the result set, using Google Maps
	And I change my location 
	And I check search again for nearby stations
	Then I should get a new list of nearby stations
	And I check the distance to each station in the result set, using Google Maps again
	
