/*
 * Copyright (c) 2010-2015 Norbert Bartels
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.restfb.integration;

import com.restfb.integration.base.RestFbIntegrationTestBase;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.types.Post;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Test;

public class PostSearchITCase extends RestFbIntegrationTestBase {

  @Test
  public void tesPostSearchV1_0() {
    DefaultFacebookClient facebookClient = new DefaultFacebookClient(getTestSettings().getUserAccessToken(), Version.VERSION_1_0);
    Connection publicSearch =
        facebookClient.fetchConnection("search", Post.class, Parameter.with("q", "watermelon"),
          Parameter.with("type", "post"));

    assertNotNull(((Post) publicSearch.getData().get(0)).getId());
  }

  @Test(expected = FacebookOAuthException.class)
  public void tesPostSearchV2_0() {
    DefaultFacebookClient facebookClient = new DefaultFacebookClient(getTestSettings().getUserAccessToken(), Version.VERSION_2_0);
    Connection publicSearch =
        facebookClient.fetchConnection("search", Post.class, Parameter.with("q", "watermelon"),
          Parameter.with("type", "post"));

    fail("facebook should not allow this public search");
  }

}
