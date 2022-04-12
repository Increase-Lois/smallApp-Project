package africa.semicolon.sendAm.controllers;

import africa.semicolon.sendAm.dtos.requests.AddPackageRequest;
import africa.semicolon.sendAm.dtos.requests.UpdateTrackingInfoRequest;
import africa.semicolon.sendAm.dtos.responses.TrackingPackageResponse;
import africa.semicolon.sendAm.dtos.responses.UpdateTrackingInfoResponse;
import africa.semicolon.sendAm.dtos.responses.AddPackageResponse;
import africa.semicolon.sendAm.services.PackageServices;
import africa.semicolon.sendAm.services.PackageServicesImpl;
import org.springframework.web.bind.annotation.*;

@RestController
public class PackageController {
    private PackageServices services = new PackageServicesImpl();

    @PostMapping("/addPackage")
    public AddPackageResponse addNewPackage(@RequestBody AddPackageRequest myPackage){
        return services.addPackage(myPackage);
    }

    @GetMapping("/{trackingNumber}")
    public TrackingPackageResponse trackPackage(@PathVariable int trackingNumber){
        return services.trackPackage(trackingNumber);
    }

    @PatchMapping("/updateStatus")
    public UpdateTrackingInfoResponse updateTrackingInfo(@RequestBody UpdateTrackingInfoRequest request){
        return  services.updateTrackingInfo(request);
    }
}
