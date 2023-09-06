import HomeIcon from "@mui/icons-material/Home";
import Person2Icon from "@mui/icons-material/Person2";
import AssignmentTurnedInIcon from "@mui/icons-material/AssignmentTurnedIn";
import WebStoriesIcon from "@mui/icons-material/WebStories";
import ForumIcon from "@mui/icons-material/Forum";
import LocalPostOfficeIcon from "@mui/icons-material/LocalPostOffice";

export const mainMenuItem = [
  {
    name: "Home",
    icon: <HomeIcon />,
  },
  {
    name: "Profile",
    icon: <Person2Icon />,
  },
  {
    name: "Task Center",
    icon: <AssignmentTurnedInIcon />,
  },
  {
    name: "stories",
    icon: <WebStoriesIcon />,
  },
];

export const otherMenuItem = [
  {
    name: "Community",
    icon: <ForumIcon />,
  },
  {
    name: "Suggestions",
    icon: <LocalPostOfficeIcon />,
  },
];