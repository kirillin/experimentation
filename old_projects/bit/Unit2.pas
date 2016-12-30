unit Unit2;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, Vcl.Menus, Unit1, Vcl.StdCtrls,
  Vcl.ExtCtrls;

type
  TForm2 = class(TForm)
    MainMenu1: TMainMenu;
    File1: TMenuItem;
    Exit1: TMenuItem;
    Users1: TMenuItem;
    New1: TMenuItem;
    Logout1: TMenuItem;
    Label1: TLabel;
    Timer1: TTimer;
    Label2: TLabel;
    Timer2: TTimer;
    procedure Exit1Click(Sender: TObject);
    procedure Logout1Click(Sender: TObject);
    procedure New1Click(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure FormShow(Sender: TObject);
    procedure Timer1Timer(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure Timer2Timer(Sender: TObject);

  private
    { Private declarations }
  public
    { Public declarations }
  end;
const
  T_LOGOUT = 10;
var
  Form2: TForm2;
  time, last_x: longint;

implementation

{$R *.dfm}

Uses Unit3;

procedure TForm2.Exit1Click(Sender: TObject);
begin
  Form2.Close;
end;

procedure TForm2.FormClose(Sender: TObject; var Action: TCloseAction);
begin
  Form3.Close;
end;

procedure TForm2.FormCreate(Sender: TObject);
begin
  Form2.Timer2.Enabled := true;
  time := T_LOGOUT;
end;

procedure TForm2.FormShow(Sender: TObject);
begin
  time := T_LOGOUT;
  Form2.Timer2.Enabled := true;
  Form2.Label1.Caption := 'Привет, ' + Unit3.login + '!';
end;

procedure TForm2.Logout1Click(Sender: TObject);
begin
  Form2.Visible := false;
  Form3.Show;
  Unit3.login := '';
end;

procedure TForm2.New1Click(Sender: TObject);
begin
  Form1.Show;
  Form2.Visible := false;
end;

procedure TForm2.Timer1Timer(Sender: TObject);
begin
  time := time - 1;
  if (time = 0) then
  begin
    Form2.Timer1.Enabled := false;
    Form3.Show;
    Form2.Visible := false;
  end;
  Form2.Label2.Caption := 'Выход из сессии через: ' + IntToStr(time);
end;

procedure TForm2.Timer2Timer(Sender: TObject);
begin
  if (not Form2.Active) then
    Form2.Timer1.Enabled := true
  else
    Form2.Timer1.Enabled := false;
end;

end.
