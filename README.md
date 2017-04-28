# Reversi
### Architecture Design
##### Behavioural Diagrams
- Application State Machine:
 [![AppStateMachine](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/check-point/behavioural-diagrams/app-state-machine.png?raw=true)](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/check-point/behavioural-diagrams/app-state-machine.png?raw=true)
- Game Logic State Machine
 [![GameStateMachine](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/check-point/behavioural-diagrams/game-state-machine.png?raw=true)](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/check-point/behavioural-diagrams/game-state-machine.png?raw=true)
##### Design Patterns
- Singleton
- Strategy - the games will have different AI difficulties, which differ in the way their moves are selected
- Command / Action / Transaction - implement undo (?)
- Model-View-Controller (possibly Model-View)
### GUI Design
##### Features:
- Single Player (play against AI)
- Two Players 
-- Same Device
-- Different Devices (over network)
- Possibility to change some visual aspects of the game and program
#### Mockups:
[![MainMenu](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/check-point/gui-mockups/Main%20Menu.png?raw=true)](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/check-point/gui-mockups/Main%20Menu.png?raw=true)
[![GameScreen](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/check-point/gui-mockups/Game%20Screen.png?raw=true)](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/check-point/gui-mockups/Game%20Screen.png?raw=true)
[![TwoPlayersMenu](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/check-point/gui-mockups/Two%20Players%20Menu.png?raw=true)](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/check-point/gui-mockups/Two%20Players%20Menu.png?raw=true)
[![DifferentDevices](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/check-point/gui-mockups/Different%20Devices%20Screen.png?raw=true)](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/check-point/gui-mockups/Different%20Devices%20Screen.png?raw=true)

### Test Design
##### Expected Unit Tests
- Test proper game initialization - starting pieces on correct locations
- Test turn attribution - turns aren't necessarily rotative
- Test legal moves on the current turn - to verify move when player is human, and later to test decision when AI is implemented
- Test a move's effect on the board - verify if pieces that should rotate after a move are actually rotated
- Test score update and display - a player's score is the number of pieces they have on currently on the board
- Test AI move choice 
- Test game sate change when player uses undo functionality
- Test case where game finishes because the board is full
- Test case where game finishes because there are no more valid moves for either players
- Test victory attribution upon game completion
- Test network connection (?)
