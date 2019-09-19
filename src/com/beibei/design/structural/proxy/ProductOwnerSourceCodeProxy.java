package com.beibei.design.structural.proxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ProductOwnerSourceCodeProxy {

    public static void main(String[] arg) throws Exception {
        Class<?> c = getProxyClass();
        Constructor<?>[] constructor = c.getConstructors();
        Object POProxy = constructor[0].newInstance("Ross");
        Method defineBackLog = c.getDeclaredMethod("defineBackLog");
        defineBackLog.invoke(POProxy);
    }

    private static String getSourceCode() {
        String src = "package com.beibei.design.structural.proxy;\n\n"
                + "public final class ProductOwnerSCProxy {\n"
                + "\tprivate String name;\n\n"
                + "\tpublic ProductOwnerSCProxy(String name){\n"
                + "\t\tthis.name = name;\n" + "\t}\n\n"
                + "\t\tpublic void defineBackLog(){\n"
                + "\t\tSystem.out.println(\"PO writes some document before defining BackLog\");"
                + "\t\tSystem.out.println(\"PO: \" + name + \" defines Backlog.\");}}\n";
        return src;
    }

    private static String createJavaFile(String sourceCode) {
        String fileName = "/Users/anbeibei/AndroidStudioProjects/2019/DesignPattern/src/com/beibei/design/structural/proxy/ProductOwnerSCProxy.java";
        File javaFile = new File(fileName);
        Writer writer;
        try {
            writer = new FileWriter(javaFile);
            writer.write(sourceCode);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    private static void compile(String fileName) {
        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager sjfm = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> iter = sjfm.getJavaFileObjects(fileName);
            JavaCompiler.CompilationTask ct = compiler.getTask(null, sjfm, null, null, null, iter);
            ct.call();
            sjfm.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Class<?> loadClass() {
        URL[] urls;
        String path = "file:///Users/anbeibei/AndroidStudioProjects/2019/DesignPattern/src/com/beibei/design/structural/";
        Class<?> c = null;
        try {
            urls = new URL[]{(new URL(path))};
            URLClassLoader ul = new URLClassLoader(urls);
            c = ul.loadClass("com.beibei.design.structural.proxy.ProductOwnerSCProxy");
            ul.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }

    private static Class<?> getProxyClass() {
        String sourceCode = getSourceCode();
        String javaFile = createJavaFile(sourceCode);
        compile(javaFile);
        return loadClass();
    }
}