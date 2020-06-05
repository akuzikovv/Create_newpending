Feature: AR status
  Scenario:  AR status
    When Create Booking using request API
      |"school_id":"ef1e8d35-5619-4a40-a76e-cd6b4eab07d6"|
      |"bookingName":"Booking for cleared teacher  4day"|
      |"bookingDescription":"Booking Description text"|
      |"other_age_group":""                           |
      |"dates":[{"date":"2020-05-28T08:30:00+02:00","fullDay":true,"start_time":"2020-05-28T08:30:00+02:00","end_time":"2020-05-28T16:00:00+02:00"}]|
      |"ageGroupIds":[{"age_group_id":"19abfc90-f45b-476b-920f-b05e6e3124a0"}]                                                              |
      |"schoolDocumentsIds":{"school_document_id":"9c78412f-fe27-4fcc-b527-405e903810e2"}                                                   |
      |"applyUsersIds":[{"user_id":"c93f0da1-4131-408a-8d09-eed7e5766a44","user_type":"TEACHER"}]                                           |
      |"teachersTypeIdsSchema":[{"teacher_type_id":"59153f42-83b1-4964-b3ad-5a50226c342a"},{"teacher_type_id":"5d714ae1-4fb4-4b89-a927-47c4396c418e"}]|
      |"creator_id":"f161024f-7d3a-4738-a804-8d9bfeeb9453"                                                                                            |
      |"anonymisation":false                                                                                                                          |
      |"documents":null                                                                                                                               |
      |"subjectIds":[{"subject_id":"e0eb4195-27e9-4a20-8a6a-c994d008cc61"}]                                                                           |
    When Accept booking as cleared teacher using request API
      |"userId":"c93f0da1-4131-408a-8d09-eed7e5766a44"|
      |"schoolId":"ef1e8d35-5619-4a40-a76e-cd6b4eab07d6"  |

