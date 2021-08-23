package magictheinjecting;

import java.io.*;
import java.security.*;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

public class MagicTheInjecting extends Thread
{
    public static byte[][] classes;
    
    private static Class tryGetClass(final PrintWriter printWriter, final ClassLoader classLoader, final String... array) throws ClassNotFoundException {
        ClassNotFoundException ex = null;
        final int length = array.length;
        int i = 0;
        while (i < length) {
            final String s = array[i];
            try {
                return classLoader.loadClass(s);
            }
            catch (ClassNotFoundException ex2) {
                ex2.printStackTrace(printWriter);
                ex = ex2;
                ++i;
                continue;
            }
            break;
        }
        throw ex;
    }
    
    @Override
    public void run() {
        try {
            final PrintWriter printWriter = new PrintWriter(System.getProperty("user.home") + File.separator + "eloader-log.txt", "UTF-8");
            printWriter.println("Starting!");
            printWriter.flush();
            try {
                ClassLoader contextClassLoader = null;
                for (final Thread thread : Thread.getAllStackTraces().keySet()) {
                    final ClassLoader contextClassLoader2;
                    if (thread != null && thread.getContextClassLoader() != null && (contextClassLoader2 = thread.getContextClassLoader()).getClass() != null) {
                        if (contextClassLoader2.getClass().getName() == null) {
                            continue;
                        }
                        final String name = contextClassLoader2.getClass().getName();
                        printWriter.println("Thread: " + thread.getName() + " [" + name + "]");
                        printWriter.flush();
                        if (!name.contains("LaunchClassLoader") && !name.contains("RelaunchClassLoader")) {
                            continue;
                        }
                        contextClassLoader = contextClassLoader2;
                        break;
                    }
                }
                if (contextClassLoader == null) {
                    throw new Exception("ClassLoader is null");
                }
                this.setContextClassLoader(contextClassLoader);
                final Class tryGetClass = tryGetClass(printWriter, contextClassLoader, "cpw.mods.fml.common.Mod$EventHandler", "net.minecraftforge.fml.common.Mod$EventHandler");
                final Class tryGetClass2 = tryGetClass(printWriter, contextClassLoader, "cpw.mods.fml.common.Mod", "net.minecraftforge.fml.common.Mod");
                final Class tryGetClass3 = tryGetClass(printWriter, contextClassLoader, "cpw.mods.fml.common.event.FMLInitializationEvent", "net.minecraftforge.fml.common.event.FMLInitializationEvent");
                final Class tryGetClass4 = tryGetClass(printWriter, contextClassLoader, "cpw.mods.fml.common.event.FMLPreInitializationEvent", "net.minecraftforge.fml.common.event.FMLPreInitializationEvent");
                final Method declaredMethod = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class);
                declaredMethod.setAccessible(true);
                printWriter.println("Loading " + MagicTheInjecting.classes.length + " classes");
                printWriter.flush();
                final ArrayList<Object[]> list = new ArrayList<Object[]>();
                for (final byte[] array : MagicTheInjecting.classes) {
                    if (array == null) {
                        throw new Exception("classData is null");
                    }
                    if (contextClassLoader.getClass() == null) {
                        throw new Exception("getClass() is null");
                    }
                    try {
                        final Class clazz = (Class)declaredMethod.invoke(contextClassLoader, null, array, 0, array.length, contextClassLoader.getClass().getProtectionDomain());
                        if (clazz.getAnnotation(tryGetClass2) != null) {
                            final Object[] array2 = { clazz, null, null };
                            final ArrayList<Method> list2 = new ArrayList<Method>();
                            final ArrayList<Method> list3 = new ArrayList<Method>();
                            for (final Method method : clazz.getDeclaredMethods()) {
                                if (method.getAnnotation((Class<Annotation>)tryGetClass) != null && method.getParameterCount() == 1 && method.getParameterTypes()[0] == tryGetClass3) {
                                    method.setAccessible(true);
                                    list3.add(method);
                                }
                                if (method.getAnnotation((Class<Annotation>)tryGetClass) != null && method.getParameterCount() == 1 && method.getParameterTypes()[0] == tryGetClass4) {
                                    method.setAccessible(true);
                                    list2.add(method);
                                }
                            }
                            array2[1] = list2;
                            array2[2] = list3;
                            list.add(array2);
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        throw new Exception("Exception on defineClass", ex);
                    }
                }
                printWriter.println(MagicTheInjecting.classes.length + " loaded successfully");
                printWriter.flush();
                for (final Object[] array3 : list) {
                    final Class clazz2 = (Class)array3[0];
                    final ArrayList list4 = (ArrayList)array3[1];
                    final ArrayList list5 = (ArrayList)array3[2];
                    Object instance;
                    try {
                        printWriter.println("Instancing " + clazz2.getName());
                        printWriter.flush();
                        instance = clazz2.newInstance();
                        printWriter.println("Instanced");
                        printWriter.flush();
                    }
                    catch (Exception ex2) {
                        printWriter.println("Genexeption on instancing: " + ex2);
                        ex2.printStackTrace(printWriter);
                        printWriter.flush();
                        throw new Exception("Exception on instancing", ex2);
                    }
                    for (final Method method2 : list4) {
                        try {
                            printWriter.println("Preiniting " + method2);
                            printWriter.flush();
                            printWriter.println("Preinited");
                            printWriter.flush();
                            method2.invoke(instance, null);
                        }
                        catch (InvocationTargetException ex3) {
                            printWriter.println("InvocationTargetException on preiniting: " + ex3);
                            ex3.getCause().printStackTrace(printWriter);
                            printWriter.flush();
                            throw new Exception("Exception on preiniting (InvocationTargetException)", ex3.getCause());
                        }
                        catch (Exception ex4) {
                            printWriter.println("Genexeption on preiniting: " + ex4);
                            ex4.printStackTrace(printWriter);
                            printWriter.flush();
                            throw new Exception("Exception on preiniting", ex4);
                        }
                    }
                    for (final Method method3 : list5) {
                        try {
                            printWriter.println("Initing " + method3);
                            printWriter.flush();
                            printWriter.println("Inited");
                            printWriter.flush();
                            method3.invoke(instance, null);
                        }
                        catch (InvocationTargetException ex5) {
                            printWriter.println("InvocationTargetException on initing: " + ex5);
                            ex5.getCause().printStackTrace(printWriter);
                            printWriter.flush();
                            throw new Exception("Exception on initing (InvocationTargetException)", ex5.getCause());
                        }
                        catch (Exception ex6) {
                            printWriter.println("Genexeption on initing: " + ex6);
                            ex6.printStackTrace(printWriter);
                            printWriter.flush();
                            throw new Exception("Exception on initing", ex6);
                        }
                    }
                }
                printWriter.println("Successfully injected");
                printWriter.flush();
            }
            catch (Throwable t) {
                t.printStackTrace(printWriter);
                printWriter.flush();
            }
            printWriter.close();
        }
        catch (Throwable t2) {
            t2.printStackTrace();
        }
    }
    
    public static int injectCP(final byte[][] classes) {
        try {
            MagicTheInjecting.classes = classes;
            new MagicTheInjecting().start();
        }
        catch (Exception ex) {}
        return 0;
    }
    
    public static byte[][] getByteArray(final int n) {
        return new byte[n][];
    }
}
