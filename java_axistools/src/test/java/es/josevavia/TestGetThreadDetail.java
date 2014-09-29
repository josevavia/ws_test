package es.josevavia;

import cfs_ws.ws_get_thread_detail.*;
import cfs_ws.ws_get_thread_detail.Cfs_WebserviceBindingStub;
import cfs_ws.ws_get_thread_detail.Cfs_WebserviceLocator;
import cfs_ws.ws_get_thread_detail.Login;
import cfs_ws.ws_get_thread_detail.Process_result;
import cfs_ws.ws_get_thread_detail.Query_filter;
import cfs_ws.ws_get_thread_detail.Thread;
import cfs_ws.ws_query_threads_list.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.math.BigInteger;

/**
 * Unit test for simple App.
 */
public class TestGetThreadDetail
    extends TestCase
{

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestGetThreadDetail(String testName)
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TestGetThreadDetail.class );
    }


    public void test_GetThreadDetail() throws Exception {
        System.out.println("test_GetThreadDetail");

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

        String version = "3.3";

        Login login = new Login();
        login.setUser("WS_1_jviader@confirmsign.com");
        login.setPassword("6f399b8102ce33b5cee222f478d7f614");

        Query_filter query_filter = new Query_filter();
        String cfs_code = "1140929122508_3387_1_338519_d3cad";
        query_filter.setCfscode(cfs_code);


        try {
            Process_result value  = binding.getThreadDetail(version, login, query_filter);
            System.out.println("Code: "+value.getCode());

            Thread thread = value.getThread();
            assertEquals(cfs_code, thread.getCfscode());

            System.out.println("Cfscode: "+thread.getCfscode());
        } catch (Exception e) {
            // System.out.println("Exception: "+e.getMessage());
            // e.printStackTrace();
        }
        // TBD - validate results

        assertEquals(true, true);
    }

}
