package com.example.project_transition.dto;

public enum SocialProvider {

    FACEBOOK("facebook"), GOOGLE("google"), GITHUB("github"), LOCAL("local");

    private String providerType;

    public String getProviderType() {
        return providerType;
    }

    SocialProvider(final String providerType) {
        this.providerType = providerType;
    }

}
