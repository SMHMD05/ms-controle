package or.sid.billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ConsulConfigRestController {
    @Autowired
    private MyConsulConfig myConsulConfig;
    @Autowired
    private MyVaultConfig myVaultConfig;

  //@Value("{token.accessTokenTimeout}")
    //private Long accessTokenTimeout;

  //@Value("{token.refreshTokenTimeout}")
  ///  private Long refreshTokenTimeout;


  @GetMapping("/myConfig")
  public Map<String, Object> myConfig(){
   return Map.of("consulConfig",myConsulConfig,"vaultConfig",myVaultConfig);
  }


}
