package com.venus.rest.test;

import com.venus.rest.VenusRestClient;
import com.venus.rest.VenusRestResponse;

import org.junit.Assert;
import org.junit.Test;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

public class LoginTest {

  /* test login */
  @Test
  public void testLogin() throws Exception {
    VenusRestClient client = new VenusRestClient();
    VenusRestResponse resp = client.login("rod", "koala");
    Assert.assertTrue("Response code", resp.getResponseCode() != HttpStatus.SC_FORBIDDEN);
  }
  
}