#include <ioavr.h>
#include <intrinsics.h>
#include "indicator.h"
#include "keyboard.h"

#define TIMER_CONST 0xb2

//программный счетчик секунд
unsigned char buf = 0;
unsigned char out[3];
unsigned char cnt = 0;

int main( void )
{
  IND_Init();
  KEYB_Init();
  ioinit();
  
  //инициализация таймера Т0 
  TIMSK = (1<<TOIE0);
  TCCR0 = (1<<CS02)|(0<<CS01)|(1<<CS00);
  TCNT0 =  TIMER_CONST;
    
  out[0] = 0;
  out[1] = 0;
  out[2] = 0;
  int res = 0;
  __enable_interrupt();
  while(1){
    KEYB_ScanKeyboard();
    buf = KEYB_GetKey();
    if(buf){    
      cnt++;
      if(cnt == 4){
        IND_Init();
        cnt = 0;
        continue;
      }
      if((buf == '*') || (buf == '#')){
        IND_Init();
        cnt = 0;
        continue;
      }
      if(cnt <= 3){
        if(cnt == 1){
          out[0] = buf;
          res = (int)out[0];
        }
        if(cnt == 2){
          out[1] = buf;
          res = 10*(int)out[0] + (int)out[1];
        }
        if(cnt == 3){
          out[2] = buf;
          res = 10 * (10*(int)out[0] + (int)out[1]) + (int)out[2];
        }
      }
      IND_Conv(res);   
    }
    __delay_cycles(1200);
  }  
  return 0;
}

//прерывания таймера Т0 - вывод на индикатор
#pragma vector = TIMER0_OVF_vect
__interrupt void Timer0_Ovf(void)
{
  TCNT0 = TIMER_CONST;
  IND_Update();
}