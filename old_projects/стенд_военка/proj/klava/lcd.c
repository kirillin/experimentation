//����� ������� ��� ������������ ��� ��������� 4-���� 	
#include "lcd.h"            


//-------����� ����� ������� lcd_com, lcd_dat----------- ��� ������� �������� �����
void lcd(unsigned char p) //"p" - ���� ������ ��� ������ 
{ 
    PORT_lcd &= 0xF0;             //������� ������� ��������
    PORT_lcd |= (p >>4);         //������� �����
    _delay_us(10);                //������������ ������� EN 
    PORT_lcd &= ~_BV(EN);        //EN=0, ����� ������ ������ � ��� 
    _delay_us(10);                //������������ ������� EN 
    PORT_lcd |= _BV(EN);          //������ EN=1 
    PORT_lcd &= 0xF0;             // ������� ������� ��������
	PORT_lcd |= (p & 0x0F);       //������� ����� 
    _delay_us(10);                //������������ ������� EN 
    PORT_lcd &= ~_BV(EN);        //EN=0, ����� ������ ������ � ��� 
    _delay_us(40);                //����� ��� ���������� ������� 
} 

/*/-------����� ����� ������� lcd_com, lcd_dat----------- ��� ������� �������� �����
void lcd(unsigned char p) //"p" - ���� ������ ��� ������ 
{ 
    PORT_lcd &= 0x0F; 
    PORT_lcd |= (p & 0xF0);    //������� ����� 
    _delay_us(10);//pause(TIME);                 //������������ ������� EN 
    PORT_lcd &= ~_BV(EN);   //EN=0, ����� ������ ������ � ��� 
    _delay_us(10);//pause(TIME);                 //������������ ������� EN 
    PORT_lcd |= _BV(EN);                        //������ EN=1 
    PORT_lcd &= 0x0F; 
	PORT_lcd |= (p << 4);      //������� ����� 
    _delay_us(10);//pause(TIME);                 //������������ ������� EN 
    PORT_lcd &= ~_BV(EN);   //EN=0, ����� ������ ������ � ��� 
    _delay_us(40);//pause(5*TIME);          //����� ��� ���������� ������� 
}*/                              
                             

//-----------������� ������ ������� � ���--------------- 
void lcd_com(unsigned char p)       //"p" - ���� ������� 
{
    PORT_lcd &= ~_BV(RS);
    PORT_lcd |= _BV(EN);      //RS=0, EN=1 
    lcd(p);   //����� ����� ����� ������� lcd_com, lcd_dat   
}                          //��������� ������� "lcd_com" 

//-----------������� ������ ������ � ���---------------- =29
void lcd_dat(unsigned char p)        //"p" - ���� ������ =30
{
    PORT_lcd |= _BV(RS) | _BV(EN);               //RS=1, EN=1 =31
    lcd(p);   //����� ����� ����� ������� lcd_com, lcd_dat =32  
}                          //��������� ������� "lcd_dat" =33


//------------������� ������������� ���----------------- =34
void lcd_init(void)       //����� 4 ���, �������� ������ =35
{
    //DDR_lcd |= 0xF0;//���� lcd �� ����� ��� ������� �������� �����
	DDR_lcd |= 0x0F;//���� lcd �� ����� ��� ������� �������� �����
	DDR_EN |= _BV(EN);
	DDR_RS |= _BV(RS);
    _delay_ms(10);
	
    lcd_com(0x33);
    _delay_ms(100);          //���������� =36
    lcd_com(0x32); 
	lcd_com(0x28);
    lcd_com(0x28);
    lcd_com(0x28);        //4 ���, 2 ������ =37
	lcd_com(0x08);             //������ ���������� ������� =38
	lcd_com(0x01); 
	_delay_ms(200);   //������� ������� =39
    lcd_com(0x06);                  //����� ������� ������ =40
    lcd_com(0x0C);    //��������� �������
	//InitCGram();
}                         

//������� ������ ������ �� FLASH �� LCD �� ������   
void show_string_flash (unsigned char adress, const unsigned char *string)
    {
	    unsigned char i;
		lcd_com (adress);
        for (i=0; i<16; i++)
	    lcd_dat(pgm_read_byte(&string[i]));  
	}

//������� ������ ������ �� ROM �� LCD �� ������   
void show_string_rom (unsigned char adress, const unsigned char *string)
    {
	    unsigned char i;
		lcd_com (adress);
        for (i=0; i<16; i++)
	    lcd_dat(string[i]);  
	}

