package org.tron.core.actuator;

import org.tron.core.vm.VMActuatorClassic;
import org.tron.core.vm.config.ConfigLoader;
import org.tron.core.vm.config.VMConfig;
import org.tron.core.vm2.TVMActuator;

public class VMFactory {

  public static VMFactory getInstance() {
    return VMFactoryInstance.INSTANCE;
  }

  private VMFactory() {

  }


  public VMActuator loadVM(boolean isConstantCall, boolean foreceUseOldVm) {
    //Load Config
    ConfigLoader.load();
    //If all config is on ,use new VMActuator
    if (!foreceUseOldVm && VMConfig.getEnergyLimitHardFork()
        && VMConfig.allowMultiSign()
        && VMConfig.allowTvmConstantinople()
        && VMConfig.allowTvmTransferTrc10()
        && VMConfig.allowTvmSolidity059()

    ) {
      return new TVMActuator(isConstantCall);
    } else {
      return new VMActuatorClassic(isConstantCall);
    }
  }


  private static class VMFactoryInstance {

    private static final VMFactory INSTANCE = new VMFactory();
  }


}