package org.volcra.hash.shell.support

class HashBannerProviderTest extends GroovyTestCase {
    def provider = new HashBannerProvider()

    void testGetBanner() {
        println provider.getBanner()
    }

    void testGetVersion() {
        assert provider.getVersion() == "0.0.1-SNAPSHOT"
    }

    void testGetWelcomeMessage() {
        assert provider.getWelcomeMessage() ==
            "Welcome to ${provider.name()} ${provider.getVersion()}.\nFor assistance press or type \"help\" then hit ENTER."
    }

    void testName() {
        assert provider.name() == "#Shell"
    }
}