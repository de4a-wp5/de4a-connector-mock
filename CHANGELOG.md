**Change-log**

- 2021-05-17
  - Updated CanonicalEvidences to the latest version of the test dataset v4.2.
- 2021-05-11
  - Updated the DO preview mock to support handle preview when not given requestId, and use return url from request object.
- 2021-05-06
  - Updated to DE4A-Commons 0.1.3
- 2021-05-04
  - First version of the DO preview mock
  - The USI transfer evidence endpoint is not used for the connector acting as dr during a request.
  Removed the DR usi transfer evidence endpoint from the mock.
- 2021-04-26
    - Added the Austrian example CanonicalEvidence for DBA pilot
- 2021-04-15
    - Change the DataOwner id to separate mock ids from real ids
- 2021-04-09 
    - Support for Kafka logging
    - Changed the T4.2 Canonical Evidence from v0.5 to v0.6

- 2021-04-07
    - Updated to support the latest version of de4a-iem
    - Added support to build to war instead of jar

- 2021-03-31
    - Split the interfaces into profiles, allowing them to be run separately.
    - Made the endpoint paths configurable
    - DR can be configured to fill the evidence by making an request to a DO.
    - Updated EvidenceId CompanyRegistration to the full urn `urn:de4a-eu:CanonicalEvidenceType::CompanyRegistration`

- 2021-03-24
    - Updated to the latest xml-schema [definition](https://github.com/de4a-wp5/xml-schemas/tree/ef08001696bac65cbf71c84726d3e0aa48a8579a).
    - Changed the T4.2 Canonical Evidence from v0.4 to v0.5
    - Changed EvidenceId from 'CompanyInfo' to 'CompanyRegistration'
    - Removed the old IDK related endpoints.
    - Updated examples

