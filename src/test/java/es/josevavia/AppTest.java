package es.josevavia;

import es.josevavia.cfs_ws_test.post_thread.*;
import es.josevavia.cfs_ws_test.post_thread.Thread;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.math.BigInteger;
import java.rmi.RemoteException;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws Exception {
        System.out.println("Hello world!");

        Cfs_WebserviceBindingStub binding;

        try {
            binding = (Cfs_WebserviceBindingStub) new Cfs_WebserviceLocator().getcfs_WebservicePort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        String version = "3.3";

        Login login = new Login();
        login.setUser("WS_1_jviader@confirmsign.com");
        login.setPassword("6f399b8102ce33b5cee222f478d7f614");

        Smtp smtp = new Smtp();
        smtp.setServer_name("ssl://smtp.gmail.com");
        smtp.setPort(BigInteger.valueOf(467));
        smtp.setUser("jviader@confirmsign.com");
        smtp.setPassword("Carlos7Chaouen7");

        Email email = new Email();
        email.setReceiver_email("jviader@confirmsign.com");
        email.setSmtp(smtp);

        Notification_channel notification_channel = new Notification_channel();
        notification_channel.setEmail(email);

        Attachment attachment1 = new Attachment();
        attachment1.setFilename("test1.pdf");
        attachment1.setFiledata("abc");

        Attachment attachment2 = new Attachment();
        attachment2.setFilename("test2.pdf");
        attachment2.setFiledata("abc");

        Attachment attachment3 = new Attachment();
        attachment3.setFilename("test3.pdf");
        attachment3.setFiledata("abc");

        Attachments attachments = new Attachments();
        attachments.setAttachment(new Attachment[]{
                attachment1,
                attachment2,
                attachment3
        });

        Thread thread = new Thread();
        thread.setExternal_id("0");
        thread.setSubject("Este es un asunto de prueba desde webservice");
        thread.setContent("Contenido del mensaje, enviando por axis");
        thread.setThread_type("CERTIFIED");
        thread.setNotification_channel(notification_channel);
        thread.setAttachments(attachments);

        try {
            Process_result value  = binding.postThread(version, login, thread);
            System.out.println("Code: "+value.getCode());
            System.out.println("Cfscode: "+value.getCfscode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        // TBD - validate results

        assertEquals(true, true);
    }
}
