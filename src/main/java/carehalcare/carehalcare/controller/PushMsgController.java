package carehalcare.carehalcare.controller;

import carehalcare.carehalcare.service.PushMsgService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(tags="푸시 메시지 API")
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class PushMsgController {
    private final PushMsgService pushMsgService;

    @PostMapping("/pushmsg")
    public ResponseEntity<Object> getFcmToken(@RequestParam String userId, String fcmToken){
        pushMsgService.getFcmToken(userId, fcmToken);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /*@GetMapping("/pushmsg")
    public List<PatientInfoPushMsgDto> findCuserIdByPuserId(@RequestParam String puserId){
        return pushMsgService.
    }*/
}
