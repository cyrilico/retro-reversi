# Reversi
<img src='https://bettercodehub.com/edge/badge/cyrilico/LPOO1617_T1G7?branch=master&token=628dabbd02be15b31f0908f603e84e21d00e6a1a'>

### Game setup
To install the game, simply open the .apk file found below on Google Play and accept the permissions it requests you (used for multiplayer through Google Play Services API purposes).

<a href='https://play.google.com/store/apps/details?id=feup.lpoo.reversi&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img width="250px" alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png'/></a>

### Project setup
 ##### Android SDK Requirements
 - Google Play Services
 - Google Repository

To open the project, one must install Android Studio, available 
[here](https://developer.android.com/studio/index.html "Android Studio download page"). To open the project, in the starting screen import directly from version control, Github. It'll clone the repository and open the project immediately. Note that the application was developed using gradle version 2.3.2 and SDK build tools version 25.0.3 (at least for the SDK build tools, further updates have been released in the last few days). If you wish to use different versions, be sure the necessary build.gradle files are updated.

**Note -** Play Games Services won't work when running from Android Studio because of authentication requisites.

### User Manual

##### Main Menu

When the app is opened, you'll face the main menu, where you'll be prompted to sign in to Google Play Games. Here you can select the type of match you're playing, Sign In/Out, or check your achievements:

<img width="250px" alt='Main Menu' src='https://github.com/cyrilico/LPOO1617_T1G7/blob/master/screenshots/main-menu.png?raw=true'/> <img width="250px" alt='Achievements' src='https://github.com/cyrilico/LPOO1617_T1G7/blob/master/screenshots/achievements.png?raw=true'/>

##### Singleplayer / Multiplayer Menus

In singleplayer mode, you can choose the AI's difficulty, aswell as the color you're playing with (don't forget black plays first!). In multiplayer mode, you can choose to play either locally or online. On the multiplayer screen, you can either play locally or online against a randomly selected opponent. The match is turn based (meaning you can leave the game after each turn), and you can check your matches on the 'Check Games' option.

<img width="250px" alt='Single Player' src='https://github.com/cyrilico/LPOO1617_T1G7/blob/master/screenshots/single-player.png?raw=true'/> <img width="250px" alt='Multiplayer' src='https://github.com/cyrilico/LPOO1617_T1G7/blob/master/screenshots/multiplayer.png?raw=true'/> <img width="250px" alt='Check Games' src='https://github.com/cyrilico/LPOO1617_T1G7/blob/master/screenshots/online-matches.png?raw=true'/>

##### Match Screen

Here is where all the magic happens. On singleplayer or local multiplayer there's a button whre you can undo the board moves all the way to the initial position.

<img width="250px" alt='Game Screen' src='https://github.com/cyrilico/LPOO1617_T1G7/blob/master/screenshots/in-game.png?raw=true'/>

### UML Diagrams
##### Model Diagram
 [![Model](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/uml/model.png?raw=true)](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/uml/model.png?raw=true)
 
##### View & Presenter Diagram
 [![Model](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/uml/view%20&%20presenter.png?raw=true)](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/uml/view%20&%20presenter.png?raw=true)

##### State Diagrams
- App State Machine
[![AppStateMachine](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/uml/app%20state.png?raw=true)](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/uml/app%20state.png?raw=true)
- Game Logic State Machine
 [![GameStateMachine](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/uml/game%20logic.png?raw=true)](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/uml/game%20logic.png?raw=true)


### Design Patterns
- **Strategy** - An AI's moves are chosen through an algorithm. Different AI difficulties have different algorithms for the way it chooses a move
- **Model-View-Presenter** - To separate the components' representation from its logic and relations and facilitate unit testing
- **Memento** - Implement 'Undo last play' functionality (only on local gameplay)

### Other Notes

##### Relevant Design Decisions
 - Choosing MVP over MVC as the main architectural design pattern turned out to be very useful, given the 'button-based' interface for both in-game and menu controls
 - Having the game logic implemented seperately from the rest of the application allowed to easily write tests to check its functionalities
 - The early on research and structural design regading viewports, layouts and other libGDX related visual elemements allowed us to properly make a responsive app that works across multiple devices
 - The well-design separation of concerns regarding the main components made the AI integration alongside the user controls seamless
  - The same applied to the online multiplayer integration, however the Play Games API integration was a lot of work
 - Having each visual board position handle its logic allowed us to gradually implement additional visual functionalities, such as animations and placement suggestions, while leaving the game logic intact.
 

##### Final Notes
- Relatively to the initial thought application structure, some changes were made throughout the development process but no big refactoring processes were thought to be necessary
- The Google Play Services API integration and online multiplayer, during the final part of development, were considered the hardest parts to implement due to the lack of previous knowledge on working with servers and application/data synchronization through multiple devices
- With this project we mainly learned the importance of a well thought architecture since the beginning of the development
- Overall, we spent roughly 200 hours on the project and the work was equally divided


