package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
@ImportResource("classpath:config.xml")
public class IndexController {
    @Autowired
    private Connection connection;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        String[] actions = new String[] {
                "/detect-device",
                "/rest/service/greeting",
                "/test-context",
        };
        model.addAttribute("actions", actions);
        return "index";
    }

    @RequestMapping("/detect-device")
    public @ResponseBody String detectDevice(Device device) {
        String deviceType = "unknown";
        if (device.isNormal()) {
            deviceType = "normal";
        } else if (device.isMobile()) {
            deviceType = "mobile";
        } else if (device.isTablet()) {
            deviceType = "tablet";
        }
        return "Hello " + deviceType + " browser!";
    }

    @RequestMapping("/test-context")
    public @ResponseBody String testContext(Device device) {
        return "Hello " + connection.getUrl() + " browser!";
    }
}
