package es.josevavia;

import cfs_ws.ws_query_threads_list.*;
import cfs_ws.ws_query_threads_list.Thread;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.math.BigInteger;
import java.util.Iterator;

/**
 * Unit test for simple App.
 */
public class TestQueryThreadsList
    extends TestCase
{

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestQueryThreadsList(String testName)
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TestQueryThreadsList.class );
    }

    public void test_QueryThreadsList() throws Exception {
        System.out.println("test_QueryThreadsList");

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
        query_filter.setPage_number(BigInteger.valueOf(1));
        query_filter.setThreads_per_page(BigInteger.valueOf(250));


        try {
            Process_result value  = binding.queryThreadsList(version, login, query_filter);
            System.out.println("Code: "+value.getCode());

            long count = value.getThreads_count().longValue();
            System.out.println("Threads count: "+count);

            if (count > 0) {
                for (int i=0; i<count; i++) {
                    System.out.println("Cfscode: "+value.getThreads().getThread(i).getCfscode());
                }
            }

        } catch (Exception e) {
            //System.out.println("Exception: "+e.getMessage());
            // e.printStackTrace();
        }
        // TBD - validate results

        assertEquals(true, true);


    }
}
