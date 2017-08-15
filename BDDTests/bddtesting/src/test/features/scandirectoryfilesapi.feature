Feature: To verify the a Webservcice API returns data in a JSON object
  As a consumer of an API 
  I want to verify that when I submit an HTTP request at an API endpoint
  I get my data as a json object or as an HTML page

  Scenario Outline: Verify the Webservice ScanDirectoryFiles returns JSON data on a valid HTTP Get method
    Given I have endpoint url as <url>
    When I submit <http_request> emthod request with parameters as <get_parameters>
    Then I should get json array with 'KnownTypes' with count of records
    And  I should get json array with 'UnknownTypes' with count of records
    And the data format as <json>
    Examples:
    | url                     | get_paramters | json                                                                                |
    | localhost:8080/ScanFile | drive=C&path=/| _filename:String,_mimeType:String,_size:string,_extension:string,_knownContent:bool |

    Scenario: Verify the webservice ScanDirectoryFiles returns HTML on HTTP Get method when no files are found
      Given I have endpoint url as <url>
      When I submit <http_request> method request with parameters as <get_parameters>
      Then I should see page with header 'No ddiles foundget json array with 'KnownTypes' with count of records
      And  I should get json array with 'UnknownTypes' with count of records
      And the data format as <json>
        | url                     | get_paramters | json                                                                                |
        | localhost:8080/ScanFile | drive=C&path=/| _filename:String,_mimeType:String,_size:string,_extension:string,_knownContent:bool |

  Scenario: Verify the webservice ScanDirectoryFiles returns HTML on HTTP Get method when directory does not exist
    Given I have endpoint url as <url>
    When I submit <http_request> method request with parameters as <get_parameters>
    Then I should see page with header 'No files found get json array with 'KnownTypes' with count of records
    And  I should get json array with 'UnknownTypes' with count of records
    And the data format as <json>
      | url                     | get_parameters | json                                                                                |
      | localhost:8080/ScanFile | drive=C&path=/| _filename:String,_mimeType:String,_size:string,_extension:string,_knownContent:bool |

  Scenario Outline: Verify the webservice ScanDirectoryFiles returns files of given type
    Given I have endpoint url as <url>
    When I submit <http_request> method request with parameters as <get_parameters>
    Then I should see page with header 'No files found get json array with 'KnownTypes' with count of records
    And  I should get json array with 'UnknownTypes' with count of records
    And the data format as <json>
    Examples:
      | url                     | get_parameters                                   | json                                                                                |
      | localhost:8080/ScanFile | drive=c&mime=ini                                 | _filename:String,_mimeType:String,_size:string,_extension:string,_knownContent:bool |
      | localhost:8080/ScanFile | mime=csv                                         | _filename:String,_mimeType:String,_size:string,_extension:string,_knownContent:bool |
      | localhost:8080/ScanFile | mime=excel                                       | _filename:String,_mimeType:String,_size:string,_extension:string,_knownContent:bool |
      | localhost:8080/ScanFile | drive=c:&path=BDDTesting/features&mime=feature   | _filename:String,_mimeType:String,_size:string,_extension:string,_knownContent:bool |

