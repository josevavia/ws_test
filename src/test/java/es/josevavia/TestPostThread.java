package es.josevavia;

import cfs_ws.ws_post_thread.*;
import cfs_ws.ws_post_thread.Thread;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.math.BigInteger;

/**
 * Unit test for simple App.
 */
public class TestPostThread
    extends TestCase
{

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestPostThread(String testName)
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TestPostThread.class );
    }


    public void test_PostThread() throws Exception {
        System.out.println("test_PostThread");

        cfs_ws.ws_post_thread.Cfs_WebserviceBindingStub binding;

        try {
            binding = (cfs_ws.ws_post_thread.Cfs_WebserviceBindingStub) new cfs_ws.ws_post_thread.Cfs_WebserviceLocator().getcfs_WebservicePort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        String version = "3.3";

        cfs_ws.ws_post_thread.Login login = new cfs_ws.ws_post_thread.Login();
        login.setUser("WS_1_jviader@confirmsign.com");
        login.setPassword("6f399b8102ce33b5cee222f478d7f614");

        Smtp smtp = new Smtp();
        smtp.setServer_name("ssl://smtp.gmail.com");
        smtp.setPort(BigInteger.valueOf(467));
        smtp.setUser("username@gmail.com");
        smtp.setPassword("ThisIsMyFakePassword");

        cfs_ws.ws_post_thread.Email email = new cfs_ws.ws_post_thread.Email();
        email.setReceiver_email("receiver@gmail.com");
        email.setSmtp(smtp);

        cfs_ws.ws_post_thread.Notification_channel notification_channel = new cfs_ws.ws_post_thread.Notification_channel();
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

        Attachment[] attachments_list = new Attachment[3];
        attachments_list[0] = attachment1;
        attachments_list[1] = attachment2;
        attachments_list[2] = attachment3;

        Attachments attachments = new Attachments();
        attachments.setAttachment(attachments_list);

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
            // System.out.println("Exception: "+e.getMessage());
            // e.printStackTrace();
        }
        // TBD - validate results

        assertEquals(true, true);
    }
}
