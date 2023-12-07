package com.example.deliverable_1;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClubDiscoveryTest {

    @Test
    public void testClubDiscoveryCreation() {
        ClubDiscovery clubDiscovery = new ClubDiscovery();
        assertNotNull(clubDiscovery);
    }

    @Test
    public void testClubListInitialization() {
        ClubDiscovery clubDiscovery = new ClubDiscovery();
        assertNotNull(clubDiscovery.clubList);
        assertEquals(0, clubDiscovery.clubList.size());
    }

    @Test
    public void testAdapterInitialization() {
        ClubDiscovery clubDiscovery = new ClubDiscovery();
        assertNotNull(clubDiscovery.adapterSearchBar);
    }

    @Test
    public void testSearchViewInitialization() {
        ClubDiscovery clubDiscovery = new ClubDiscovery();
        assertNotNull(clubDiscovery.searchView);
    }

    @Test
    public void testListViewInitialization() {
        ClubDiscovery clubDiscovery = new ClubDiscovery();
        assertNotNull(clubDiscovery.listView);
    }

}
