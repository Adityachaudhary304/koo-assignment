# Koo Assignment
This repository contains the Android project files presented as a submission of assignment to KooApp India.

### Details
 This app uses GoRest  [API](https://gorest.co.in/public/v1/posts) to fetch post details and then display Post Title, Post ID and Post Body in a vertical scrolling list of cards.

### External libraries & Best practices

* **Retrofit** : It is a type safe HTTP client for Android and Java. It was used in this app to make async API calls to get the Post details.
* **MVVM Architecture** : This app uses MVVM architecture as it is an industry standard showcased by Google in its developers conference Google I/O 19. This architecture supports separation of data from lifecycle and UI changes thus providing for a better user experience. 
* **Recycler View** : For implementing the vertical scrolling list, Recycler View was used as it makes it easy to efficiently display large sets of data beacuse it recycles views and reuses the view for new items that have scrolled on the screen. 
