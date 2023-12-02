package carPooling;

public interface DriverRatingInterface {
  /**
   * Sets the security rating of the driver.
   *
   * @param securityRating The security rating to be set.
   * @throws IllegalArgumentException If the provided rating is outside the valid
   *                                  range [0, 5].
   */
  void setSecurityRating(float securityRating) throws IllegalArgumentException;

  /**
   * Gets the security rating of the driver.
   *
   * @return The security rating of the driver.
   */
  int getSecurityRating();

  /**
   * Sets the cleanliness rating of the driver.
   *
   * @param cleanlinessRating The cleanliness rating to be set.
   * @throws IllegalArgumentException If the provided rating is outside the valid
   *                                  range [0, 5].
   */
  void setCleanlinessRating(float cleanlinessRating) throws IllegalArgumentException;

  /**
   * Gets the cleanliness rating of the driver.
   *
   * @return The cleanliness rating of the driver.
   */
  int getCleanlinessRating();
}
