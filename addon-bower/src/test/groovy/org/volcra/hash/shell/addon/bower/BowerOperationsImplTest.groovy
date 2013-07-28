package org.volcra.hash.shell.addon.bower

import org.springframework.web.client.RestTemplate

/**
 *
 * @author Emanuelle Gardu&ntilde;o
 */
class BowerOperationsImplTest extends GroovyTestCase {
    BowerOperationsImpl fixture

    void setUp() {
        fixture = new BowerOperationsImpl(restTemplate: new RestTemplate(), components: new File('.'),
                endpoint: new URI('https://bower-component-list.herokuapp.com'))
    }

    void testInfo() {

    }

    void testInstall() {

    }

    void testList() {
        fixture.list()
    }

    void testLookup() {

    }

    void testSearch() {
        fixture.search 'jquery'
    }

    void testUninstall() {

    }
}
