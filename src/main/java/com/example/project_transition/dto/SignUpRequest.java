package com.example.project_transition.dto;

import com.example.project_transition.validator.PasswordMatches;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Chinna
 * @since 26/3/18
 */
@Data
@PasswordMatches
public class SignUpRequest {

	private Long userID;

	private String providerUserId;

	@NotEmpty
	private String username;

	@NotEmpty
	private String email;

	private SocialProvider socialProvider;

	@Size(min = 6, message = "Try one with at least 6 characters")
	private String password;

	@NotEmpty
	private String matchingPassword;

	public SignUpRequest(String providerUserId, String username, String email, String password, SocialProvider socialProvider) {
		this.providerUserId = providerUserId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.socialProvider = socialProvider;
	}

	public SignUpRequest() {
	}

	public static Builder getBuilder() {
		return new Builder();
	}

	public static class Builder {
		private String providerUserID;
		private String username;
		private String email;
		private String password;
		private SocialProvider socialProvider;

		public Builder addProviderUserID(final String userID) {
			this.providerUserID = userID;
			return this;
		}

		public Builder addUsername(final String username) {
			this.username = username;
			return this;
		}

		public Builder addEmail(final String email) {
			this.email = email;
			return this;
		}

		public Builder addPassword(final String password) {
			this.password = password;
			return this;
		}

		public Builder addSocialProvider(final SocialProvider socialProvider) {
			this.socialProvider = socialProvider;
			return this;
		}

		public SignUpRequest build() {
			return new SignUpRequest(providerUserID, username, email, password, socialProvider);
		}
	}
}
