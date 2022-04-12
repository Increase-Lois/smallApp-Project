package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.data.models.Package;
import africa.semicolon.sendAm.data.models.PackageDescription;
import africa.semicolon.sendAm.dtos.requests.AddPackageRequest;
import africa.semicolon.sendAm.dtos.requests.UpdateTrackingInfoRequest;
import africa.semicolon.sendAm.dtos.responses.AddPackageResponse;
import africa.semicolon.sendAm.dtos.responses.TrackingPackageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackageServicesImplTest {
    PackageServices packageService;

    @BeforeEach
    void setUp(){
        packageService = new PackageServicesImpl();
    }
    @Test
    void package_can_be_added_test_to_repository(){
        AddPackageRequest packageToAdd = new AddPackageRequest();
//        packageToAdd.setId(1);
        PackageDescription description = new PackageDescription();
        description.setName("My first package");
        description.setWeightInGrammes(25);
        packageService.addPackage(packageToAdd);
        assertEquals(1, packageService.getRepository().count());
    }

//    @Test
//    void test_that_packages_with_duplicate_id_throws_exception(){
//        AddPackageRequest packageToAdd = new AddPackageRequest();
////        packageToAdd.setId(1);
//        PackageDescription description = new PackageDescription();
//        description.setName("My first package");
//        description.setWeightInGrammes(25);
//        packageToAdd.setName(description.getName());
//        packageToAdd.setWeightInGrammes(description.getWeightInGrammes());
////        packageService.addPackage(packageToAdd);
//        assertThrows(RegisterFailureException.class, ()-> packageService.addPackage(packageToAdd));
//    }

    @Test
    void test_that_adding_a_package_give_correct_response() {
        AddPackageRequest packageToAdd = new AddPackageRequest();
//        packageToAdd.setId(1);
        PackageDescription description = new PackageDescription();
        description.setName("My first package");
        description.setWeightInGrammes(25);
        packageToAdd.setName(description.getName());
        packageToAdd.setWeightInGrammes(description.getWeightInGrammes());
//        System.out.println(packageToAdd.getDescription().toString());
        AddPackageResponse packageResponse= packageService.addPackage(packageToAdd);
        assertEquals(1, packageResponse.getId());
        assertEquals("My first package", packageResponse.getName());
        assertEquals(25, packageResponse.getWeightInGrammes());
    }

    @Test
    void test_that_adding_another_package_give_correct_response() {
        AddPackageRequest packageToAdd = new AddPackageRequest();
//        packageToAdd.setId(1);
        PackageDescription description = new PackageDescription();
        description.setName("My first package");
        description.setWeightInGrammes(25);

        AddPackageRequest packageToAdd2 = new AddPackageRequest();
//        packageToAdd2.setId(2);
        PackageDescription description2 = new PackageDescription();
        description2.setName("My second package");
        description2.setWeightInGrammes(20);

//        System.out.println(packageToAdd.getDescription().toString());
        packageToAdd2.setName(description2.getName());
        packageToAdd2.setWeightInGrammes(description2.getWeightInGrammes());
        AddPackageResponse packageResponse= packageService.addPackage(packageToAdd);
        AddPackageResponse packageResponse2= packageService.addPackage(packageToAdd2);
        assertEquals(2, packageResponse2.getId());
        assertEquals("My second package", packageResponse2.getName());
        assertEquals(20, packageResponse2.getWeightInGrammes());
    }


    @Test
    void testThatStatusIsUpDated_WhenANewPackageIsCreated(){
        AddPackageRequest packageToAdd = new AddPackageRequest();
        packageToAdd.setName("Boxer");
        packageToAdd.setWeightInGrammes(12);
        packageService.addPackage(packageToAdd);

//        Package savedPackage = packageService.getRepository().findById(1);
        Package savedPackage = packageService.getRepository().findById(1);
        assertEquals(1, savedPackage.getStatusList().size());
    }

    public void savePackage(){
        AddPackageRequest packageToAdd = new AddPackageRequest();
        packageToAdd.setName("Boxer");
        packageToAdd.setWeightInGrammes(12);
        packageService.addPackage(packageToAdd);
    }

    @Test
    void addStatusTest(){
        //given there is a status
        savePackage();
        // when i update status
        UpdateTrackingInfoRequest updateTrackingInfoRequest = new UpdateTrackingInfoRequest();
//        UpdateTrackingInfoResponse response = packageService.updateTrackingInfo(updateTrackingInfoRequest);
        updateTrackingInfoRequest.setTrackingNumber(1);
        updateTrackingInfoRequest.setTrackingInfo("Arrived");
        packageService.updateTrackingInfo(updateTrackingInfoRequest);
        //confirm status count
        Package savedPackage = packageService.getRepository().findById(1);
        assertEquals(2, savedPackage.getStatusList().size());
        // confirm status msg

    }

    @Test
    void trackPackageTest(){
        savePackage();
        TrackingPackageResponse trackingInfo = packageService.trackPackage(1);

    }
//    @Test
//    void test_that_package_info_can_be_updated(){
//        AddPackageRequest packageToAdd = new AddPackageRequest();
//        packageToAdd.setId(1);
//        PackageDescription description = new PackageDescription();
//        description.setName("My first package");
//        description.setWeightInGrammes(25);
//        packageToAdd.setDescription(description);
//        AddPackageResponse packageResponse= packageService.addPackage(packageToAdd);
//        packageService.updatePackage(packageToAdd, "Chocolate");
//    }


}