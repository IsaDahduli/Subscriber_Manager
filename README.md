An app that allows an Admin to Add, Retrieve, Update or Delete a subscriber <br/>
<br/>
Written in: <br/>
Kotlin Front End <br/>
Java Back End <br/>
PHP Back End <br/>
MySQL DB <br/>
XML Layout <br/>

Uses XAMPP, place the PHP files in the htdocs directory <br/>

Uses Retrofit and GSON <br/>
<br/> 
Make sure to replace the emulator IP private const val BASE_URL = "http://10.0.2.2/" with one of your liking <br/>
<br/>
To Login, use Admin/Admin for username and Password <br/>

The fields are : <br/>
1-MSISDN (Mobile Station International Subscriber Directory Number) <br/>
2-ICCID (ntegrated Circuit Card Identification) <br/>
3-Charging Type <br/>
4-Sim Profile ID <br/>
5-Service Type <br/>
6-MVNO Name (Mobile Virtual Network Operator) <br/>
7-Tariff Code <br/>
8-TAC (Type Allocation Code) <br/>
9-Brand <br/>
10-Model <br/>

To implement the DB run this SQL querry first: <br/>
 <br/>
CREATE TABLE subscribers ( <br/> 
    id INT AUTO_INCREMENT PRIMARY KEY, <br/>
    msisdn VARCHAR(20) NOT NULL, <br/>
    iccid VARCHAR(20) NOT NULL, <br/>
    charging_type ENUM('Prepaid', 'PostPaid') NOT NULL, <br/>
    sim_profile_id VARCHAR(20) NOT NULL, <br/>
    service_type VARCHAR(50) NOT NULL, <br/>
    mvno_name VARCHAR(50), <br/>
    tariff_code VARCHAR(50), <br/>
    tac VARCHAR(20), <br/>
    brand VARCHAR(50), <br/>
    model VARCHAR(50) <br/>
);
