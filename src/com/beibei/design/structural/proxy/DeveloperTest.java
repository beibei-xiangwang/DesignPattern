
package com.beibei.design.structural.proxy;

public class DeveloperTest {
    public static void main(String[] args) {
//        IDeveloper andi = new Developer("Andi");
//        andi.writeCode();

//        DeveloperProxy andiProxy = new DeveloperProxy(andi);
//        andiProxy.writeCode();

//        IDeveloper andi = new Developer("Andi");
//        Tester bob = new Tester("Bob");
//        IDeveloper andiProxy = (IDeveloper) new EngineerProxyDynamic().bind(andi);
//        ITester bobProxy = (ITester) new EngineerProxyDynamic().bind(bob);
//        andiProxy.writeCode();
//        bobProxy.doTesting();
//
//        ProductOwner po = new ProductOwner("Ross");
//        ProductOwner poProxy = (ProductOwner) new EngineerProxyDynamic().bind(po);
//        poProxy.defineBackLog();

        ProductOwner ross = new ProductOwner("Ross");
        ProductOwner rossProxy = (ProductOwner) new EngineerCGLibProxy().bind(ross);
        rossProxy.defineBackLog();

    }
}