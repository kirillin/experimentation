{
  The author is Artemov Kirill
  Problem 3. Hourglass
  KSTU INTER 2014
}

unit Unit1;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, Vcl.StdCtrls, Vcl.ExtCtrls;


const
  HEIGHT = 150;
  WIDTH = 80;
  FIELD = 10;
  MASK_HEIGHT = HEIGHT+2*FIELD;
  MASK_WIDTH = WIDTH+2*FIELD;
  W_LINE = 2;
  COLOR_SAND = clyellow;
  COLOR_LINE = clwhite;
  COLOR_BG = clblack;

type
  TForm1 = class(TForm)
    Image1: TImage;
    Button1: TButton;
    Button2: TButton;
    Timer1: TTimer;
    Button3: TButton;
    Label1: TLabel;
    Timer2: TTimer;
    Label2: TLabel;
    Label3: TLabel;
    procedure Button1Click(Sender: TObject);
    procedure Button2Click(Sender: TObject);
    procedure Timer1Timer(Sender: TObject);
    procedure Button3Click(Sender: TObject);
    procedure Timer2Timer(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;
var
  Form1: TForm1;
  // mask contains all field of drawing image which consist of
  // sand, hourglass and background
  mask, nextMask: array[1..MASK_HEIGHT,1..MASK_WIDTH] of integer;
  // point which begins filling sand
  xSand, ySand: longint;
  count: longint;
  chance: array[1..100] of integer;

implementation

{$R *.dfm}

{
  Procedure generates mask which contains:
    '1' - sand;
    '2' - background;
    '3' - profile of hourglass
}
procedure fillMask();
var
  i, j: Integer;
begin
  for i := 1 to MASK_HEIGHT do
    for j := 1 to MASK_WIDTH do
      if (Form1.Image1.Canvas.Pixels[j,i] = COLOR_SAND) then
        mask[i,j] := 1
      else if (Form1.Image1.Canvas.Pixels[j,i] = COLOR_BG) then
        mask[i,j] := 2
      else if (Form1.Image1.Canvas.Pixels[j,i] = COLOR_LINE) then
        mask[i,j] := 3;
end;

{
  Procedure draws image for generated mask
}
procedure fillImage();
var
  i, j: Integer;
begin
  for i := FIELD to HEIGHT+FIELD do
    for j := FIELD to WIDTH+FIELD do
      if (mask[i,j] = 2) then
        Form1.Image1.Canvas.Pixels[j,i] := COLOR_BG
      else if (mask[i,j] = 1) then
        Form1.Image1.Canvas.Pixels[j,i] := COLOR_SAND
      else if (mask[i,j] = 3) then
        Form1.Image1.Canvas.Pixels[j,i] := COLOR_LINE;
end;

{
  Procedure draws default position of sand in hourglass
}
procedure fillSand(x,y: longint);
var
  direction: -1..1;
begin
  direction := 1;
  while (x <= MASK_HEIGHT div 2) do
  begin
      if (mask[x,y] <> 3) then
      begin
        mask[x,y] := 1;
        y := y + direction;
      end else
      begin
        inc(x);
        if (direction = 1) then
          direction := -1
        else
          direction := 1;
        while (mask[x,y] = 3) do
          y := y + direction;
      end;
  end;
end;

{
   Procedure for making decisions about
    the direction of grain of sand(or pixel :) ) fall
}
procedure movePixel(i,j: longint);
var
  rnd: longint;
begin
  if (mask[i,j] = 1) then
  begin
    // random digit from 0 to 2 with defined the probability of occurrence
    rnd := chance[random(100)];
    if ((rnd = 2) and (mask[i+1,j] = 2)) then
    begin
      if (mask[i+1,j] <> 3) then
      begin
        mask[i,j] := 2;
        mask[i+1,j] := 1
      end;
    end
    else if ((rnd = 2) and (mask[i+1,j+1] = 2)) then
    begin
      if (mask[i+1,j+1] <> 3) then
      begin
        mask[i,j] := 2;
        mask[i+1,j+1] := 1;
      end;
    end
    else if ((rnd = 2) and (mask[i+1,j-1] = 2)) then
    begin
      if (mask[i+1,j-1] <> 3) then
      begin
        mask[i,j] := 2;
        mask[i+1,j-1] := 1;
      end;
    end
    else if ((rnd = 0) and (mask[i,j-1] = 2)) then
    begin
      if (mask[i+1,j-1] <> 3) then
      begin
        mask[i,j] := 2;
        mask[i,j-1] := 1;
      end;
    end
    else if ((rnd = 0) and (mask[i,j+1] = 2)) then
    begin
      if (mask[i+1,j-1] <> 3) then
      begin
        mask[i,j] := 2;
        mask[i,j+1] := 1;
      end;
    end;
  end;
end;

{
  Procedure computes new mask for simulating the movement of sand
}
procedure computeMask();
var
  i, j: longint;
begin
  for i := MASK_HEIGHT-Unit1.FIELD downto Unit1.FIELD  do
  begin
    for j := MASK_WIDTH div 2  downto Unit1.FIELD do
    begin
      movePixel(i,j);
    end;
    for j := MASK_WIDTH div 2 to MASK_WIDTH-Unit1.FIELD do
    begin
      movePixel(i,j);
    end;
  end;
end;

procedure initChanceArray();
var
  i: longint;
begin
  for i := 1 to 100 do
  begin
    if (i <= 4) then chance[i] := 0 else
    if (i > 4) and (i <= 40) then chance[i] := 1 else
    if (i > 40) then chance[i] := 2 else
  end;
end;

procedure TForm1.Button1Click(Sender: TObject);
var
  stepByVert, stepByHor, clearance, sandHeight: longint;
  x, y: longint;
begin
  Form1.Label1.Caption := '0';
  count := 0;
  Form1.Timer1.Enabled := false;
  initChanceArray();
  // some actions for initialization houtglass and other...
  Form1.Image1.Height := Unit1.HEIGHT + Unit1.FIELD*2;
  Form1.Image1.Width := Unit1.WIDTH + Unit1.FIELD*2;
  Form1.Image1.Canvas.Pen.Color := Unit1.COLOR_BG;
  Form1.Image1.Canvas.Brush.Color := Unit1.COLOR_BG;
  Form1.Image1.Canvas.Rectangle(0,0,Form1.Image1.Width,Form1.Image1.Height);
  stepByVert := Unit1.HEIGHT div 3;
  stepByHor := Unit1.WIDTH div 2;
  clearance := 10;
  sandHeight := stepByVert - stepByVert*50 div 100;
  x := Unit1.FIELD;
  y := Unit1.FIELD;
  // coordinate of start filling sand
  ySand := Unit1.WIDTH div 2;
  xSand := y + (Unit1.HEIGHT div 2 - sandHeight-y);
  Form1.Image1.Canvas.Pen.Width := Unit1.W_LINE;
  Form1.Image1.Canvas.Pen.Color := Unit1.COLOR_LINE;
  with Form1.Image1.Canvas do
  begin
    MoveTo(x,y);
//    LineTo(x,y + stepByVert);
    LineTo(
            x + stepByHor - clearance div 2,
            y + stepByVert + stepByVert div 2 - clearance div 2);
    LineTo(
            x + stepByHor - clearance div 2,
            y + stepByVert + stepByVert div 2 + clearance div 2);
//    LineTo(x,y + 2 * stepByVert);
    LineTo(x,y + 3 * stepByVert);
    LineTo(x + 2 * stepByHor,y + 3 * stepByVert);
//    LineTo(x + 2 * stepByHor,y + 2 * stepByVert);
    LineTo(
            x + stepByHor + clearance div 2,
            y + stepByVert + stepByVert div 2 + clearance div 2);
    LineTo(
            x + stepByHor + clearance div 2,
            y + stepByVert + stepByVert div 2 - clearance div 2);
//    LineTo(x + 2 * stepByHor,y + stepByVert);
    LineTo(x + 2 * stepByHor,y);
    LineTo(x,y);
  end;
  fillMask();
  fillSand(xSand,ySand);
  fillImage();
end;

procedure TForm1.Button2Click(Sender: TObject);
begin
  Form1.Timer1.Enabled := true;
  Form1.Timer2.Enabled := true;
end;

procedure TForm1.Button3Click(Sender: TObject);
begin
  Form1.Close;
end;

procedure TForm1.Timer1Timer(Sender: TObject);
begin
  computeMask();
  fillImage();
end;

procedure TForm1.Timer2Timer(Sender: TObject);
begin
 count := count +1;
 Form1.Label1.Caption := intToStr(count);
end;

end.
