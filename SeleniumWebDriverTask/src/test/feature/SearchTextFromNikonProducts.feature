#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)

Feature: Search text from Nikon products
	Scenario: Find text from the second most expensive Nikon product in Amazon
		Given User is on website "https://www.amazon.com"
		When User writes "Nikon" to search field
			And User sorts results from highest to lowest by price
			And User wants to see all results
			And User selects second product
		Then User sees text "Nikon D3X" in product topic
			
