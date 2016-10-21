#include <ioavr.h>
#include <intrinsics.h>


int main( void )
{
  
  PORTA |= 0x03;
  DDRA &= ~0x03;
  
  PORTB = 0x00;
  DDRB = 0xFF;
  
  PORTC = 0x00;
  DDRC = 0xFF;

  PORTD = 0x00;
  DDRD = 0xFF;
 
  
  
  while(1){
 
    if(PINA.0 == 0){
      while(!PINA.1){
        for(int i = 0; i < 8; i++){
          PORTB |= 1<<i;
          __delay_cycles(800000);      
        }
      }
    PORTB = PORTC = PORTD = 0x00;    
    }
  }
  return 0;
}
