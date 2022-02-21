package nextstep.subway.ui;

import nextstep.auth.authorization.AuthenticationPrincipal;
import nextstep.member.domain.LoginMember;
import nextstep.subway.applicaion.PathService;
import nextstep.subway.applicaion.dto.PathResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathController {
    private PathService pathService;

    public PathController(PathService pathService) {
        this.pathService = pathService;
    }

    @GetMapping("/paths")
    public ResponseEntity<PathResponse> findPath(@RequestParam Long source, @RequestParam Long target) {
        return ResponseEntity.ok(pathService.findPath(source, target));
    }

    @GetMapping("/paths/minimum-time")
    public ResponseEntity<PathResponse> findPathByDuration(@RequestParam Long source, @RequestParam Long target) {
        return ResponseEntity.ok(pathService.findPathByMinimumTime(source, target));
    }

    @GetMapping("/paths/minimum-fee")
    public ResponseEntity<PathResponse> findPathByFee(@AuthenticationPrincipal LoginMember loginMember, @RequestParam Long source, @RequestParam Long target) {
        return ResponseEntity.ok(pathService.findPathByMinimumFee(loginMember.getAge(), source, target));
    }
}
